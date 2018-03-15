package seller.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import seller.dao.ProductGroupViewDao;
import seller.entity.reference.AttributeItem;
import seller.entity.reference.GroupAttributeView;
import seller.entity.reference.ProductGroupView;

/**
 * <code>DAO</code> для работы с {@link ProductGroupView}.<br>
 * Реализует связь с БД через технологию <code>jdbc</code>.
 *
 * @author Aleksei Zabezhinsky
 */
public class ProductGroupViewJdbcDao extends JdbcDaoSupport implements ProductGroupViewDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<ProductGroupView> findAll() {
        final String sql =
                "SELECT"
              + "    pg.id, pg.name,"
              + "    ga.id AS ATTRIBUTE_ID, ga.name AS ATTRIBUTE_NAME,"
              + "    ai.id AS ATTRIBUTE_ITEM_ID, ai.name AS ATTRIBUTE_ITEM_NAME "
              + "FROM"
              + "    product_group pg"
              + "    LEFT JOIN group_attribute ga ON (pg.id = ga.group_id)"
              + "    LEFT JOIN attribute_item ai ON (ga.id = ai.attribute_id)"
              + "ORDER BY"
              + "    name, attribute_name, attribute_item_name";
        ResultSetHandler<List<ProductGroupView>> handler =
                new ResultSetHandler<List<ProductGroupView>>() {
                    @Override
                    public List<ProductGroupView> handle(ResultSet rs) throws SQLException {
                        ProductGroupView group = null;
                        GroupAttributeView attribute = null;
                        AttributeItem item = null;
                        List<ProductGroupView> list = new ArrayList<ProductGroupView>();
                        while (rs.next()) {
                            Integer groupId = rs.getInt("id");
                            if (group == null || !group.getId().equals(groupId)) {
                                group = new ProductGroupView();
                                group.setId(groupId);
                                group.setName(rs.getString("name"));
                                group.setGroupAttributes(new ArrayList<GroupAttributeView>());
                                list.add(group);
                            }
                            Integer attributeId = rs.getInt("attribute_id");
                            if (attributeId != 0
                                    && ((attribute == null || !attributeId.equals(attribute.getId())))) {
                                attribute = new GroupAttributeView();
                                attribute.setId(attributeId);
                                attribute.setGroupId(group.getId());
                                attribute.setName(rs.getString("attribute_name"));
                                attribute.setAttributeItems(new ArrayList<AttributeItem>());
                                group.getGroupAttributes().add(attribute);
                            }
                            Integer itemId = rs.getInt("attribute_item_id");
                            if (itemId != 0 && ((item == null || !itemId.equals(item.getId())))) {
                                item = new AttributeItem();
                                item.setId(itemId);
                                item.setAttributeId(attribute.getId());
                                item.setName(rs.getString("attribute_item_name"));
                                attribute.getAttributeItems().add(item);
                            }
                        }
                        return list;
                    }
        };
        return (List<ProductGroupView>) executeStatement(sql, null, handler);
    }

}
