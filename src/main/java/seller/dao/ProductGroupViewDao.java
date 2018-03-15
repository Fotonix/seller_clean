package seller.dao;

import java.util.List;

import seller.entity.reference.ProductGroupView;

/**
 * <code>DAO</code> для работы с {@link ProductGroupView}.
 *
 * @author Aleksei Zabezhinsky
 */
public interface ProductGroupViewDao {

    /**
     * Получает все группы товаров.
     *
     * @return коллекция сущностей {@link ProductGroupView}
     */
    List<ProductGroupView> findAll();

}
