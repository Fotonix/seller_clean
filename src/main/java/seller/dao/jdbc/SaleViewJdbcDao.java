package seller.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import seller.dao.SaleViewDao;
import seller.entity.sale.SaleView;

/**
 * <code>DAO</code> для работы с {@link SaleView}.<br>
 * Реализует связь с БД через технологию <code>jdbc</code>.
 *
 * @author Aleksei Zabezhinsky
 */
public class SaleViewJdbcDao extends JdbcDaoSupport implements SaleViewDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<SaleView> findAll() {
        final String sql =
                "SELECT"
              + "    id, date, held "
              + "FROM"
              + "    sale "
              + "ORDER BY"
              + "    date";
        ResultSetHandler<List<SaleView>> handler =
                new ResultSetHandler<List<SaleView>>() {
                    @Override
                    public List<SaleView> handle(ResultSet rs) throws SQLException {
                        List<SaleView> list = new ArrayList<SaleView>();
                        while (rs.next()) {
                            SaleView entity = new SaleView();
                            entity.setId(rs.getInt("id"));
                            entity.setDate(rs.getDate("date"));
                            entity.setHeld(rs.getBoolean("held"));
                            list.add(entity);
                        }
                        return list;
                    }
        };
        return (List<SaleView>) executeStatement(sql, null, handler);
    }

}
