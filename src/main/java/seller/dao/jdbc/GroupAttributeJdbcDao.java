package seller.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import seller.dao.GroupAttributeDao;
import seller.entity.reference.GroupAttribute;

/**
 * <code>DAO</code> для работы с признаком группы товара.<br>
 * Реализует связь с БД через технологию <code>jdbc</code>.
 *
 * @author Aleksei Zabezhinsky
 */
public class GroupAttributeJdbcDao extends JdbcDaoSupport implements GroupAttributeDao {

    @Override
    public GroupAttribute read(Integer id) {
        final String sql =
                "SELECT"
              + "    id, name, group_id "
              + "FROM"
              + "    group_attribute "
              + "WHERE"
              + "    id = ?";
        ResultSetHandler<GroupAttribute> handler =
                new ResultSetHandler<GroupAttribute>() {
                    @Override
                    public GroupAttribute handle(ResultSet rs) throws SQLException {
                        rs.next();
                        GroupAttribute attr = new GroupAttribute();
                        attr.setId(rs.getInt("id"));
                        attr.setName(rs.getString("name"));
                        attr.setGroupId(rs.getInt("group_id"));
                        return attr;
                    }
        };
        return (GroupAttribute) executeStatement(sql, new Object[] {id}, handler);
    }

    @Override
    public void delete(Integer id) {
        final String sql =
                "DELETE FROM "
              + "    group_attribute "
              + "WHERE id = ?";
            executeDelete(sql, new Object[] {id});
    }

    @Override
    public void create(GroupAttribute entity) {
        final String sql =
                "INSERT INTO"
              + "     group_attribute (name, group_id) "
              + "VALUES (?, ?)";
        Object[] params = new Object[2];
        params[0] = entity.getName();
        params[1] = entity.getGroupId();
        Integer id = (Integer) executeInsert(sql, params);
        entity.setId(id);
    }

    @Override
    public void update(GroupAttribute entity) {
        final String sql =
                "UPDATE"
              + "    group_attribute "
              + "SET"
              + "    name = ?, group_id = ? "
              + "WHERE id = ?";
        Object[] params = new Object[3];
        params[0] = entity.getName();
        params[1] = entity.getGroupId();
        params[2] = entity.getId();
        executeUpdate(sql, params);
    }

}
