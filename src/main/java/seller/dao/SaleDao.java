package seller.dao;

import java.util.Date;

import seller.entity.sale.Sale;

/**
 * <code>DAO</code> для работы с реализацией товара.
 *
 * @author Aleksei Zabezhinsky
 */
public interface SaleDao extends CrudDao<Integer, Sale> {

    /**
     * Получает реализацию по дате.
     *
     * @param date дата реализации
     * @return реализация
     */
    Sale readByDate(Date date);

}
