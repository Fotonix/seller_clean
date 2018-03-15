package seller.service;

import java.util.List;

import seller.entity.sale.ProductSaleItem;
import seller.entity.sale.Sale;
import seller.entity.sale.SaleItem;
import seller.entity.sale.SaleView;

/**
 * Сервис работы с реализацией товара.
 *
 * @author Aleksei Zabezhinsky
 */
public interface SaleService {

    /**
     * Получает все реализации товаров.
     *
     * @return коллекция сущностей {@link SaleView}
     */
    List<SaleView> getSaleViews();

    /**
     * Получает реализацию товара по <code>id</code>.
     *
     * @param id идентификатор сущности {@link Sale}
     * @return сущность {@link Sale}
     */
    Sale getSale(Integer id);

    /**
     * Удаляет реализации товаров.
     *
     * @param id массив идентификаторов сущности {@link Sale}
     */
    void deleteSales(Integer[] id);

    /**
     * Сохраняет (создает/обновляет) реализацию товара и items.
     *
     * @param sale сущность {@link Sale}
     * @param items коллекция сущностей {@link SaleItem}
     */
    void saveSaleWithItems(Sale sale, List<SaleItem> items);

    /**
     * Получает все реализации товаров по ID группы товаров и дате реализации.
     *
     * @param groupId группа товаров
     * @param saleId ID реализации товаров
     * @return все реализации товаров
     */
    List<ProductSaleItem> getProductSaleItems(Integer groupId, Integer saleId);

}
