package seller.dao.jdbc;

import seller.dao.ProductDao;
import seller.entity.reference.Product;

/**
 * <code>DAO</code> для работы с товаром.<br>
 * Реализует связь с БД через технологию <code>jdbc</code>.
 *
 * @author Aleksei Zabezhinsky
 */
public class ProductJdbcDao extends JdbcDaoSupport implements ProductDao {

    @Override
    public void delete(Integer id) {
        final String sql =
                "DELETE FROM "
              + "    product "
              + "WHERE id = ?";
            executeDelete(sql, new Object[] {id});
    }

    /* (non-Javadoc)
     * @see seller.dao.CrudDao#create(java.lang.Object)
     */
    @Override
    public void create(Product entity) {
        throw new UnsupportedOperationException("Method not realized");
    }

    /* (non-Javadoc)
     * @see seller.dao.CrudDao#read(java.lang.Number)
     */
    @Override
    public Product read(Integer key) {
        throw new UnsupportedOperationException("Method not realized");
    }

    /* (non-Javadoc)
     * @see seller.dao.CrudDao#update(java.lang.Object)
     */
    @Override
    public void update(Product entity) {
        throw new UnsupportedOperationException("Method not realized");
    }

}
