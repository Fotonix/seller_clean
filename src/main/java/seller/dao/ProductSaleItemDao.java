package seller.dao;

import java.util.Date;
import java.util.List;

import seller.entity.sale.ProductSaleItem;

/**
 * <code>DAO</code> для работы с реализацией в разрезе товара.
 *
 * @author Aleksei Zabezhinsky
 */
public interface ProductSaleItemDao {

    /**
     * Получает все реализации в разрезе товаров по ID группы товаров и дате реализации.
     *
     * @param groupId группа товаров
     * @param saleId ID реализации товаров
     * @param date дата реализации товаров
     * @return все реализации товаров
     */
    List<ProductSaleItem> getByFilter(Integer groupId, Integer saleId, Date date);

}
