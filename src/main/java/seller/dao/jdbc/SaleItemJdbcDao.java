package seller.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import seller.dao.SaleItemDao;
import seller.entity.sale.SaleItem;
import seller.jdbc.JdbcSupport;

/**
 * <code>DAO</code> для работы с позициями реализации товара.<br>
 * Реализует связь с БД через технологию <code>jdbc</code>.
 *
 * @author Aleksei Zabezhinsky
 */
public class SaleItemJdbcDao extends JdbcSupport implements SaleItemDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void create(SaleItem entity) {
        final String sql =
                "INSERT INTO "
              + "    sale_item (sale_id, product_id, quantity, price) "
              + "VALUES (?, ?, ?, ?)";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getSaleId());
            statement.setInt(2, entity.getProductId());
            statement.setInt(3, entity.getQuantity());
            statement.setBigDecimal(4, entity.getPrice());
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            keys.next();
            entity.setId(keys.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("SQL error", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error("Can't close SQL statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Can't close JDBC connection", e);
                }
            }
        }
    }

    @Override
    public void update(SaleItem entity) {
        final String sql =
                "UPDATE "
              + "    sale_item "
              + "SET "
              + "    sale_id = ?, product_id = ?, quantity = ?, price = ? "
              + "WHERE id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getSaleId());
            statement.setInt(2, entity.getProductId());
            statement.setInt(3, entity.getQuantity());
            statement.setBigDecimal(4, entity.getPrice());
            statement.setInt(5, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("SQL error", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error("Can't close SQL statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Can't close JDBC connection", e);
                }
            }
        }
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            return;
        }
        final String sql =
                "DELETE FROM "
              + "    sale_item "
              + "WHERE id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("SQL error", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error("Can't close SQL statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Can't close JDBC connection", e);
                }
            }
        }
    }

}
