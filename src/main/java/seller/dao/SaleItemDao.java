package seller.dao;

import seller.entity.sale.SaleItem;

/**
 * <code>DAO</code> для работы с позициями реализации товара.<br>
 *
 * @author Aleksei Zabezhinsky
 */
public interface SaleItemDao {

    /**
     * Создает позицию реализации товара.
     *
     * @param entity сущность {@link SaleItem}
     */
    void create(SaleItem entity);

    /**
     * Обновляет позицию реализации товара.
     *
     * @param entity сущность {@link SaleItem}
     */
    void update(SaleItem entity);

    /**
     * Удаляет позицию реализации товара.
     *
     * @param id сущности {@link seller.entity.sale.SaleItem}
     */
    void delete(Integer id);

}
