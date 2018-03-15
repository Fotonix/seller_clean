package seller.dao;

import java.util.List;

import seller.entity.sale.SaleView;

/**
 * <code>DAO</code> для работы с {@link SaleView}.
 *
 * @author Aleksei Zabezhinsky
 */
public interface SaleViewDao {

    /**
     * Получает все реализации товаров.
     *
     * @return коллекция сущностей {@link SaleView}
     */
    List<SaleView> findAll();

}
