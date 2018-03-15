package seller.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import seller.dao.ProductSaleItemDao;
import seller.entity.reference.Product;
import seller.entity.sale.ProductSaleItem;
import seller.entity.sale.SaleItem;

/**
 * <code>DAO</code> для работы с реализацией в разрезе товара.<br>
 * Реализует связь с БД через технологию <code>jdbc</code>.
 *
 * @author Aleksei Zabezhinsky
 */
public class ProductSaleItemJdbcDao extends JdbcDaoSupport implements ProductSaleItemDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<ProductSaleItem> getByFilter(Integer groupId, Integer saleId, Date date) {
        java.sql.Date saleDate = new java.sql.Date(date.getTime());
        if (saleId == null) {
            saleId = 0;
        }
        final String sql =
              "SELECT"
            + "    p.id AS product_id, p.name AS product_name, p.price,"
            + "    SUM(product_invoice.quantity) - coalesce(SUM(product_sale.quantity), 0) AS balance,"
            + "    si_current.id AS sale_item_id, si_current.price AS sale_item_price,"
            + "    si_current.quantity AS sale_item_quantity "
            + "FROM"
            + "    product p"
            // Получаем приход товара
            + "    LEFT JOIN ("
            + "        SELECT "
            + "            ii.product_id AS product_id, SUM(ii.quantity) AS quantity"
            + "        FROM"
            + "            invoice i"
            + "            JOIN invoice_item ii ON (i.id = ii.invoice_id)"
            + "        WHERE"
            + "            i.date <= ?"
            + "        GROUP BY"
            + "            ii.product_id"
            + "        ) product_invoice ON (p.id = product_invoice.product_id)"
            // Получаем расход товара
            + "    LEFT JOIN ("
            + "        SELECT "
            + "            si.product_id AS product_id, SUM(si.quantity) AS quantity"
            + "        FROM"
            + "            sale s"
            + "            JOIN sale_item si ON (s.id = si.sale_id)"
            + "        WHERE"
            + "            s.date <= ?"
            + "        GROUP BY"
            + "            si.product_id"
            + "        ) product_sale ON (p.id = product_sale.product_id)"
            + "    LEFT JOIN sale_item si_current ON"
            + "         (p.id = si_current.product_id AND si_current.sale_id = ?) "
            + "WHERE"
            + "    p.group_id = ? "
            + "GROUP BY"
            + "    p.id, product_name, p.group_id, p.price,"
            + "    si_current.id, si_current.price, si_current.quantity "
            + "ORDER BY"
            + "    p.name";
      List<ProductSaleItem> list = new ArrayList<ProductSaleItem>();
      Connection connection = getConnection();
      PreparedStatement statement = null;
      try {
          statement = connection.prepareStatement(sql);
          statement.setDate(1, saleDate);
          statement.setDate(2, saleDate);
          statement.setInt(3, saleId);
          statement.setInt(4, groupId);
          ResultSet result = statement.executeQuery();
          ProductSaleItem productSaleItem = null;
          while (result.next()) {
              Integer productId = result.getInt("product_id");
              if (productSaleItem == null || !productId.equals(productSaleItem.getProduct().getId())) {
                  if (productSaleItem != null) {
                      list.add(productSaleItem);
                  }
                  Product product = new Product();
                  product.setId(productId);
                  product.setName(result.getString("product_name"));
                  product.setGroupId(groupId);
                  product.setPrice(result.getBigDecimal("price"));

                  productSaleItem = new ProductSaleItem();
                  productSaleItem.setBalance(result.getInt("balance"));
                  productSaleItem.setProduct(product);
              }
              if (productSaleItem.getItems() == null) {
                  productSaleItem.setItems(new ArrayList<SaleItem>());
                  // Создаем первый item для цены по умолчанию.
                  SaleItem saleItem = new SaleItem();
                  saleItem.setProductId(productId);
                  saleItem.setPrice(productSaleItem.getProduct().getPrice());
                  saleItem.setQuantity(0);
                  productSaleItem.getItems().add(saleItem);
              }
              Integer saleItemId = result.getInt("sale_item_id");
              if (saleItemId != 0) {
                  BigDecimal price = result.getBigDecimal("sale_item_price");
                  Integer quantity = result.getInt("sale_item_quantity");
                  // Если есть item с ценой по умолчанию - в первый элемент добавляем его характеристики.
                  if (price.equals(productSaleItem.getProduct().getPrice())) {
                      SaleItem saleItem = productSaleItem.getItems().get(0);
                      saleItem.setQuantity(quantity);
                      saleItem.setId(saleItemId);
                  } else {
                      SaleItem saleItem = new SaleItem();
                      saleItem.setId(saleItemId);
                      saleItem.setProductId(productId);
                      saleItem.setPrice(price);
                      saleItem.setQuantity(quantity);
                      productSaleItem.getItems().add(saleItem);
                  }
              }
          }
          list.add(productSaleItem);
      } catch (SQLException e) {
          throw new RuntimeException("SQL error", e);
      } finally {
          if (statement != null) {
              try {
                  statement.close();
              } catch (SQLException e) {
                  logger.error("Can't close SQL statement", e);
              }
          }
          if (connection != null) {
              try {
                  connection.close();
              } catch (SQLException e) {
                  logger.error("Can't close JDBC connection", e);
              }
          }
      }
      return list;
    }

}
