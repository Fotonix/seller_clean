package seller.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import seller.dao.AttributeItemDao;
import seller.entity.reference.AttributeItem;

/**
 * <code>DAO</code> для работы с позицией признака группы товара.<br>
 * Реализует связь с БД через технологию <code>jdbc</code>.
 *
 * @author Aleksei Zabezhinsky
 */
public class AttributeItemJdbcDao extends JdbcDaoSupport implements AttributeItemDao {

    @Override
    public AttributeItem read(Integer id) {
        final String sql =
                "SELECT"
              + "    id, name, attribute_id "
              + "FROM"
              + "    attribute_item "
              + "WHERE"
              + "    id = ?";
        ResultSetHandler<AttributeItem> handler =
                new ResultSetHandler<AttributeItem>() {
                    @Override
                    public AttributeItem handle(ResultSet rs) throws SQLException {
                        rs.next();
                        AttributeItem item = new AttributeItem();
                        item.setId(rs.getInt("id"));
                        item.setName(rs.getString("name"));
                        item.setAttributeId(rs.getInt("attribute_id"));
                        return item;
                    }
        };
        return (AttributeItem) executeStatement(sql, new Object[] {id}, handler);
    }

    @Override
    public void delete(Integer id) {
        final String sql =
                "DELETE FROM "
              + "    attribute_item "
              + "WHERE id = ?";
            executeDelete(sql, new Object[] {id});
    }

    @Override
    public void create(AttributeItem entity) {
        final String sql =
                "INSERT INTO"
              + "     attribute_item (name, attribute_id) "
              + "VALUES (?, ?)";
        Object[] params = new Object[2];
        params[0] = entity.getName();
        params[1] = entity.getAttributeId();
        Integer id = (Integer) executeInsert(sql, params);
        entity.setId(id);
    }

    @Override
    public void update(AttributeItem entity) {
        final String sql =
                "UPDATE"
              + "    attribute_item "
              + "SET"
              + "    name = ?, attribute_id = ? "
              + "WHERE id = ?";
        Object[] params = new Object[3];
        params[0] = entity.getName();
        params[1] = entity.getAttributeId();
        params[2] = entity.getId();
        executeUpdate(sql, params);
    }

}
