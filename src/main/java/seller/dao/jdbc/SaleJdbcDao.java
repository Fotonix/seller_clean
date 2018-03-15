package seller.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import seller.dao.SaleDao;
import seller.entity.sale.Sale;

/**
 * <code>DAO</code> для работы с реализацией товара.<br>
 * Реализует связь с БД через технологию <code>jdbc</code>.
 *
 * @author Aleksei Zabezhinsky
 */
public class SaleJdbcDao extends JdbcDaoSupport implements SaleDao {

    @Override
    public Sale read(Integer id) {
        if (id == null) {
            return null;
        }
        final String sql =
                "SELECT"
              + "    id, date, held "
              + "FROM"
              + "    sale "
              + "WHERE"
              + "    id = ?";
        ResultSetHandler<Sale> handler =
                new ResultSetHandler<Sale>() {
                    @Override
                    public Sale handle(ResultSet rs) throws SQLException {
                        rs.next();
                        Sale sale = new Sale();
                        sale.setId(rs.getInt("id"));
                        sale.setDate(rs.getDate("date"));
                        sale.setHeld(rs.getBoolean("held"));
                        return sale;
                    }
        };
        return (Sale) executeStatement(sql, new Object[] {id}, handler);
    }

    @Override
    public void delete(Integer id) {
        final String sql =
                "DELETE FROM "
              + "    sale "
              + "WHERE id = ?";
            executeStatement(sql, new Object[] {id});
    }

    @Override
    public void create(Sale entity) {
        final String sql =
                "INSERT INTO"
              + "    sale (date, held) "
              + "VALUES (?, ?)";
        Object[] params = new Object[2];
        params[0] = new java.sql.Date(entity.getDate().getTime());
        params[1] = entity.isHeld();
        Integer id = (Integer) executeInsert(sql, params);
        entity.setId(id);
    }

    @Override
    public void update(Sale entity) {
        final String sql =
                "UPDATE"
              + "    invoice "
              + "SET"
              + "    date = ?, held = ? "
              + "WHERE id = ?";
        Object[] params = new Object[3];
        params[0] = new java.sql.Date(entity.getDate().getTime());
        params[1] = entity.isHeld();
        params[2] = entity.getId();
        executeUpdate(sql, params);
    }

    @Override
    public Sale readByDate(Date date) {
        final String sql =
                "SELECT"
              + "    id, date, held "
              + "FROM"
              + "    sale "
              + "WHERE"
              + "    date = ?";
        ResultSetHandler<Sale> handler =
                new ResultSetHandler<Sale>() {
                    @Override
                    public Sale handle(ResultSet rs) throws SQLException {
                        rs.next();
                        Sale sale = new Sale();
                        sale.setId(rs.getInt("id"));
                        sale.setDate(rs.getDate("date"));
                        sale.setHeld(rs.getBoolean("held"));
                        return sale;
                    }
        };
        Object[] params = new Object[] {new java.sql.Date(date.getTime())};
        return (Sale) executeStatement(sql, params, handler);
    }

}
