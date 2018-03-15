package seller.service;

import seller.dao.DaoFactory;
import seller.dao.ProductDao;

/**
 * Реализация сервиса {@link ProductService}.<br>
 * Реализует связь с БД через технологию <code>jdbc</code>.<br>
 * Логика доступа к БД осуществляется c использованием <code>DAO</code>,<br>
 * обеспечивает транзакционность на уровне сервиса.
 *
 * @author Aleksei Zabezhinsky
 */
public class ProductServiceJdbcImpl extends TransactionalService implements ProductService {

    private final ProductDao productDao = DaoFactory.getInstance().getProductDao();

    @Override
    public void deleteProducts(Integer[] ids) {
        startTransaction();
        for (Integer id : ids) {
            productDao.delete(id);
        }
        commitTransaction();
    }

}
