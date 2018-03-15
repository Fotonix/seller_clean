package seller.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import seller.entity.invoice.Invoice;
import seller.entity.invoice.InvoiceItem;
import seller.entity.invoice.InvoiceItemView;
import seller.entity.invoice.InvoiceView;
import seller.jdbc.JdbcSupport;

import static seller.util.StringUtils.normalize;

/**
 * Реализация сервиса {@link InvoiceService}.<br>
 * Реализует связь с БД через технологию <code>jdbc</code>.<br>
 * Логика доступа к БД осуществляется без использования <code>DAO</code>, т.к.
 * присутствует сложность обеспечивать транзакционность на уровне сервиса.
 *
 * @author Aleksei Zabezhinsky
 */
public class InvoiceServiceJdbcImpl extends JdbcSupport implements InvoiceService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<InvoiceView> getInvoiceViews() {
        final String sql =
                "SELECT "
              + "    i.id, i.date, i.series, i.held, p.name AS PRODUCER "
              + "FROM "
              + "    invoice i "
              + "    JOIN producer p ON (i.producer_id = p.id)"
              + "ORDER BY"
              + "    date, producer";
      ArrayList<InvoiceView> list = new ArrayList<InvoiceView>();
      Connection connection = getConnection();
      PreparedStatement statement = null;
      try {
          statement = connection.prepareStatement(sql);
          ResultSet result = statement.executeQuery();
          while (result.next()) {
              InvoiceView entity = new InvoiceView();
              entity.setId(result.getInt("id"));
              entity.setDate(result.getDate("date"));
              entity.setSeries(result.getString("series"));
              entity.setHeld(result.getBoolean("held"));
              entity.setProducer(result.getString("producer"));
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
    public Invoice getInvoice(Integer id) {
        final String sql =
                "SELECT "
              + "    id, date, series, producer_id, held "
              + "FROM "
              + "    invoice "
              + "WHERE"
              + "    id = ?";
      Connection connection = getConnection();
      PreparedStatement statement = null;
      Invoice invoice = null;
      try {
          statement = connection.prepareStatement(sql);
          statement.setInt(1, id);
          ResultSet result = statement.executeQuery();
          result.next();
          invoice = new Invoice();
          invoice.setId(result.getInt("id"));
          invoice.setDate(result.getDate("date"));
          invoice.setSeries(result.getString("series"));
          invoice.setProducerId(result.getInt("producer_id"));
          invoice.setHeld(result.getBoolean("held"));
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
        return invoice;
    }

    @Override
    public void deleteInvoices(Integer[] id) {
        final String sql =
                "DELETE FROM "
              + "    invoice "
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
    public void createInvoice(Invoice entity) {
        final String sql =
                "INSERT INTO "
              + "    invoice (date, series, producer_id, held) "
              + "VALUES (?, ?, ?, ?)";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, new java.sql.Date(entity.getDate().getTime()));
            statement.setString(2, normalize(entity.getSeries()));
            statement.setInt(3, entity.getProducerId());
            statement.setBoolean(4, entity.isHeld());
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
    public void updateInvoice(Invoice entity) {
        final String sql =
                "UPDATE "
              + "    invoice "
              + "SET "
              + "    date = ?, series = ?, producer_id = ?, held = ? "
              + "WHERE id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(entity.getDate().getTime()));
            statement.setString(2, normalize(entity.getSeries()));
            statement.setInt(3, entity.getProducerId());
            statement.setBoolean(4, entity.isHeld());
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
    public List<InvoiceItemView> getInvoiceItemViews(Integer invoiceId) {
        final String sql =
                "SELECT "
              + "    i.id, pg.name AS GROUP, p.name AS PRODUCT, i.quantity, i.cost, p.price "
              + "FROM "
              + "    invoice_item i"
              + "    JOIN product p ON (i.product_id = p.id)"
              + "    JOIN product_group pg ON (p.group_id = pg.id)"
              + "WHERE "
              + "    i.invoice_id = ? "
              + "ORDER BY"
              + "    pg.name, p.name, quantity";
      ArrayList<InvoiceItemView> list = new ArrayList<InvoiceItemView>();
      Connection connection = getConnection();
      PreparedStatement statement = null;
      try {
          statement = connection.prepareStatement(sql);
          statement.setInt(1, invoiceId);
          ResultSet result = statement.executeQuery();
          while (result.next()) {
              InvoiceItemView entity = new InvoiceItemView();
              entity.setId(result.getInt("id"));
              entity.setGroup(result.getString("group"));
              entity.setProduct(result.getString("product"));
              entity.setQuantity(result.getInt("quantity"));
              entity.setCost(result.getBigDecimal("cost"));
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
    public void deleteInvoiceItems(Integer[] id) {
        final String sql =
                "DELETE FROM "
              + "    invoice_item "
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
    public InvoiceItem getInvoiceItem(Integer id) {
        final String sql =
                "SELECT "
              + "    i.id, i.invoice_id, p.group_id, i.product_id, i.quantity, i.cost, p.price "
              + "FROM "
              + "    invoice_item i"
              + "    JOIN product p ON (i.product_id = p.id) "
              + "WHERE"
              + "    i.id = ?";
      Connection connection = getConnection();
      PreparedStatement statement = null;
      InvoiceItem invoiceItem = null;
      try {
          statement = connection.prepareStatement(sql);
          statement.setInt(1, id);
          ResultSet result = statement.executeQuery();
          result.next();
          invoiceItem = new InvoiceItem();
          invoiceItem.setId(result.getInt("id"));
          invoiceItem.setInvoiceId(result.getInt("invoice_id"));
          invoiceItem.setProductId(result.getInt("product_id"));
          invoiceItem.setProductGroupId(result.getInt("group_id"));
          invoiceItem.setQuantity(result.getInt("quantity"));
          invoiceItem.setCost(result.getBigDecimal("cost"));
          invoiceItem.setPrice(result.getBigDecimal("price"));
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
        return invoiceItem;
    }

    @Override
    public void createInvoiceItem(InvoiceItem entity) {
        final String sql =
                "INSERT INTO "
              + "    invoice_item (invoice_id, product_id, quantity, cost) "
              + "VALUES (?, ?, ?, ?)";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getInvoiceId());
            statement.setInt(2, entity.getProductId());
            statement.setInt(3, entity.getQuantity());
            statement.setBigDecimal(4, entity.getCost());
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            keys.next();
            entity.setId(keys.getInt(1));
            updateProductPrice(entity.getProductId(), entity.getPrice());
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
    public void updateInvoiceItem(InvoiceItem entity) {
        final String sql =
                "UPDATE "
              + "    invoice_item "
              + "SET "
              + "    invoice_id = ?, product_id = ?, quantity = ?, cost = ? "
              + "WHERE id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getInvoiceId());
            statement.setInt(2, entity.getProductId());
            statement.setInt(3, entity.getQuantity());
            statement.setBigDecimal(4, entity.getCost());
            statement.setInt(5, entity.getId());
            statement.executeUpdate();
            updateProductPrice(entity.getProductId(), entity.getPrice());
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

    /**
     * Обновляет цену товара.
     *
     * @param productId ID товара
     * @param price цена товара
     */
    private void updateProductPrice(Integer productId, BigDecimal price) {
        final String sql =
                "UPDATE "
              + "    product "
              + "SET "
              + "    price = ? "
              + "WHERE id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setBigDecimal(1, price);
            statement.setInt(2, productId);
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
