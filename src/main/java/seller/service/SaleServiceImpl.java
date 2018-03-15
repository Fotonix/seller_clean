package seller.service;

import java.util.Date;
import java.util.List;

import seller.dao.DaoFactory;
import seller.dao.ProductSaleItemDao;
import seller.dao.SaleDao;
import seller.dao.SaleItemDao;
import seller.dao.SaleViewDao;
import seller.entity.sale.ProductSaleItem;
import seller.entity.sale.Sale;
import seller.entity.sale.SaleItem;
import seller.entity.sale.SaleView;

/**
 * Реализация сервиса {@link SaleService}.<br>
 * Реализует связь с БД через технологию <code>jdbc</code>.<br>
 * Логика доступа к БД осуществляется без использования <code>DAO</code>, т.к.
 * присутствует сложность обеспечивать транзакционность на уровне сервиса.
 *
 * @author Aleksei Zabezhinsky
 */
public class SaleServiceImpl implements SaleService {

    private final SaleDao saleDao = DaoFactory.getInstance().getSaleDao();
    private final SaleItemDao saleItemDao = DaoFactory.getInstance().getSaleItemDao();
    private final SaleViewDao saleViewDao = DaoFactory.getInstance().getSaleViewDao();
    private final ProductSaleItemDao productSaleItemDao = DaoFactory.getInstance().getProductSaleItemDao();

    @Override
    public List<SaleView> getSaleViews() {
        return saleViewDao.findAll();
    }

    @Override
    public Sale getSale(Integer id) {
        return saleDao.read(id);
    }

    @Override
    public void deleteSales(Integer[] ids) {
        if (ids != null) {
            for (Integer id : ids) {
                saleDao.delete(id);
            }
        }
    }

    @Override
    public void saveSaleWithItems(Sale sale, List<SaleItem> items) {
        if (sale.getId() == null) {
            saleDao.create(sale);
        } else {
            saleDao.update(sale);
        }
        for (SaleItem item : items) {
            item.setSaleId(sale.getId());
            if (item.getQuantity() == null || item.getQuantity() == 0) {
                saleItemDao.delete(item.getId());
            } else if (item.getId() == null) {
                saleItemDao.create(item);
            } else {
                saleItemDao.update(item);
            }
        }
    }

    @Override
    public List<ProductSaleItem> getProductSaleItems(Integer groupId, Integer saleId) {
        Sale sale = saleDao.read(saleId);

        // Если не найдена реализация, значит выводим данные на текущую дату.
        Date saleDate = sale == null ? new Date() : sale.getDate();

        return productSaleItemDao.getByFilter(groupId, saleId, saleDate);
    }

}
