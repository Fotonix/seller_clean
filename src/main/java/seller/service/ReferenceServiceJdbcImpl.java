package seller.service;

import static seller.util.StringUtils.normalize;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import seller.dao.AttributeItemDao;
import seller.dao.DaoFactory;
import seller.dao.GroupAttributeDao;
import seller.dao.ProductDao;
import seller.dao.ProductGroupViewDao;
import seller.entity.reference.AttributeItem;
import seller.entity.reference.GroupAttribute;
import seller.entity.reference.Producer;
import seller.entity.reference.Product;
import seller.entity.reference.ProductGroup;
import seller.entity.reference.ProductGroupView;
import seller.entity.reference.ProductView;
import seller.jdbc.JdbcSupport;

/**
 * Реализация сервиса {@link ReferenceService}.<br>
 * Реализует связь с БД через технологию <code>jdbc</code>.<br>
 * Логика доступа к БД осуществляется без использования <code>DAO</code>, т.к.
 * присутствует сложность обеспечивать транзакционность на уровне сервиса.
 *
 * @author Aleksei Zabezhinsky
 */
public class ReferenceServiceJdbcImpl extends JdbcSupport implements ReferenceService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private GroupAttributeDao groupAttributeDao;
    //private final GroupAttributeDao groupAttributeDao = DaoFactory.getInstance().getGroupAttributeDao();
    private final AttributeItemDao attributeItemDao = DaoFactory.getInstance().getAttributeItemDao();
    @Inject
    private ProductGroupViewDao productGroupViewDao;
    //private final ProductGroupViewDao productGroupViewDao = DaoFactory.getInstance().getProductGroupViewDao();
    private final ProductDao productDao = DaoFactory.getInstance().getProductDao();

    @Override
    public Product getProduct(Integer id) {
        final String sql =
                "SELECT "
              + "    id, name, group_id, price "
              + "FROM "
              + "    product "
              + "WHERE"
              + "    id = ?";
      Connection connection = getConnection();
      PreparedStatement statement = null;
      Product product = null;
      try {
          statement = connection.prepareStatement(sql);
          statement.setInt(1, id);
          ResultSet result = statement.executeQuery();
          result.next();
          product = new Product();
          product.setId(result.getInt("id"));
          product.setName(result.getString("name"));
          product.setGroupId(result.getInt("group_id"));
          product.setPrice(result.getBigDecimal("price"));
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
        return product;
    }

    @Override
    public List<Product> getProductsByGroupId(Integer groupId) {
        final String sql =
                "SELECT "
              + "    p.id, p.name, p.price "
              + "FROM "
              + "    product p "
              + "    JOIN product_group pg ON (p.group_id = pg.id) "
              + "WHERE "
              + "    pg.id = ? "
              + "ORDER BY"
              + "    name";
      ArrayList<Product> list = new ArrayList<Product>();
      Connection connection = getConnection();
      PreparedStatement statement = null;
      try {
          statement = connection.prepareStatement(sql);
          statement.setInt(1, groupId);
          ResultSet result = statement.executeQuery();
          while (result.next()) {
              Product entity = new Product();
              entity.setId(result.getInt("id"));
              entity.setName(result.getString("name"));
              entity.setPrice(result.getBigDecimal("price"));
              list.add(entity);
          }
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
      return list;
    }

    @Override
    public void createProduct(Product entity) {
        final String sql =
                "INSERT INTO "
              + "    product (name, group_id, price) "
              + "VALUES (?, ?, ?)";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, normalize(entity.getName()));
            statement.setInt(2, entity.getGroupId());
            statement.setBigDecimal(3, entity.getPrice());
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
    public void updateProduct(Product entity) {
        final String sql =
                "UPDATE "
              + "    product "
              + "SET"
              + "    name = ?, group_id = ?, price = ? "
              + "WHERE id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, normalize(entity.getName()));
            statement.setInt(2, entity.getGroupId());
            statement.setBigDecimal(3, entity.getPrice());
            statement.setInt(4, entity.getId());
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
    public void deleteProducts(Integer[] ids) {
        for (Integer id : ids) {
            productDao.delete(id);
        }
    }

    @Override
    public List<ProductView> getProductViews() {
        final String sql =
                  "SELECT "
                + "    p.id, p.name, pg.name AS GROUP "
                + "FROM "
                + "    product p "
                + "    JOIN product_group pg ON (p.group_id = pg.id)"
                + "ORDER BY"
                + "    name";
        ArrayList<ProductView> list = new ArrayList<ProductView>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ProductView entity = new ProductView();
                entity.setId(result.getInt("id"));
                entity.setName(result.getString("name"));
                entity.setGroup(result.getString("group"));
                list.add(entity);
            }
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
        return list;
    }

    @Override
    public List<ProductGroup> getProductGroups() {
        final String sql =
                "SELECT "
              + "    id, name "
              + "FROM "
              + "    product_group "
              + "ORDER BY"
              + "    name";
      ArrayList<ProductGroup> list = new ArrayList<ProductGroup>();
      Connection connection = getConnection();
      PreparedStatement statement = null;
      try {
          statement = connection.prepareStatement(sql);
          ResultSet result = statement.executeQuery();
          while (result.next()) {
              ProductGroup entity = new ProductGroup();
              entity.setId(result.getInt("id"));
              entity.setName(result.getString("name"));
              list.add(entity);
          }
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
      return list;
    }

    @Override
    public ProductGroup getProductGroup(Integer id) {
        final String sql =
                "SELECT "
              + "    id, name "
              + "FROM "
              + "    product_group "
              + "WHERE"
              + "    id = ?";
      Connection connection = getConnection();
      PreparedStatement statement = null;
      ProductGroup productGroup = null;
      try {
          statement = connection.prepareStatement(sql);
          statement.setInt(1, id);
          ResultSet result = statement.executeQuery();
          result.next();
          productGroup = new ProductGroup();
          productGroup.setId(result.getInt("id"));
          productGroup.setName(result.getString("name"));
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
        return productGroup;
    }

    @Override
    public void createProductGroup(ProductGroup entity) {
        final String sql =
                "INSERT INTO "
              + "    product_group (name) "
              + "VALUES (?)";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, normalize(entity.getName()));
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
    public void updateProductGroup(ProductGroup entity) {
        final String sql =
                "UPDATE "
              + "    product_group "
              + "SET"
              + "    name = ? "
              + "WHERE id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, normalize(entity.getName()));
            statement.setInt(2, entity.getId());
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
    public void deleteProductGroups(Integer[] id) {
        final String sql =
                "DELETE FROM "
              + "    product_group "
              + "WHERE id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < id.length; i++) {
                statement.setInt(1, id[i]);
                statement.addBatch();
            }
            statement.executeBatch();
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
    public List<Producer> getProducers() {
        final String sql =
                "SELECT "
              + "    id, name "
              + "FROM "
              + "    producer "
              + "ORDER BY"
              + "    name";
      ArrayList<Producer> list = new ArrayList<Producer>();
      Connection connection = getConnection();
      PreparedStatement statement = null;
      try {
          statement = connection.prepareStatement(sql);
          ResultSet result = statement.executeQuery();
          while (result.next()) {
              Producer entity = new Producer();
              entity.setId(result.getInt("id"));
              entity.setName(result.getString("name"));
              list.add(entity);
          }
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
      return list;
    }

    @Override
    public Producer getProducer(Integer id) {
        final String sql =
                "SELECT "
              + "    id, name "
              + "FROM "
              + "    producer "
              + "WHERE"
              + "    id = ?";
      Connection connection = getConnection();
      PreparedStatement statement = null;
      Producer producer = null;
      try {
          statement = connection.prepareStatement(sql);
          statement.setInt(1, id);
          ResultSet result = statement.executeQuery();
          result.next();
          producer = new Producer();
          producer.setId(result.getInt("id"));
          producer.setName(result.getString("name"));
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
        return producer;
    }

    @Override
    public void createProducer(Producer entity) {
        final String sql =
                "INSERT INTO "
              + "    producer (name) "
              + "VALUES (?)";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, normalize(entity.getName()));
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
    public void updateProducer(Producer entity) {
        final String sql =
                "UPDATE "
              + "    producer "
              + "SET"
              + "    name = ? "
              + "WHERE id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, normalize(entity.getName()));
            statement.setInt(2, entity.getId());
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
    public void deleteProducers(Integer[] id) {
        final String sql =
                "DELETE FROM "
              + "    producer "
              + "WHERE id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < id.length; i++) {
                statement.setInt(1, id[i]);
                statement.addBatch();
            }
            statement.executeBatch();
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
    public GroupAttribute getGroupAttribute(Integer id) {
        return groupAttributeDao.read(id);
    }

    @Override
    public void createGroupAttribute(GroupAttribute entity) {
        groupAttributeDao.create(entity);
    }

    @Override
    public void updateGroupAttribute(GroupAttribute entity) {
        groupAttributeDao.update(entity);
    }

    @Override
    public void deleteGroupAttribute(Integer id) {
        groupAttributeDao.delete(id);
    }

    @Override
    public AttributeItem getAttributeItem(Integer id) {
        return attributeItemDao.read(id);
    }

    @Override
    public void createAttributeItem(AttributeItem entity) {
        attributeItemDao.create(entity);
    }

    @Override
    public void updateAttributeItem(AttributeItem entity) {
        attributeItemDao.update(entity);
    }

    @Override
    public void deleteAttributeItem(Integer id) {
        attributeItemDao.delete(id);
    }

    @Override
    public List<ProductGroupView> getProductGroupViews() {
        return productGroupViewDao.findAll();
    }

}
