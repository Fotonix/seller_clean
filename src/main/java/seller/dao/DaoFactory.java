package seller.dao;

import seller.dao.jdbc.AttributeItemJdbcDao;
import seller.dao.jdbc.GroupAttributeJdbcDao;
import seller.dao.jdbc.ProductGroupViewJdbcDao;
import seller.dao.jdbc.ProductJdbcDao;
import seller.dao.jdbc.ProductSaleItemJdbcDao;
import seller.dao.jdbc.SaleItemJdbcDao;
import seller.dao.jdbc.SaleJdbcDao;
import seller.dao.jdbc.SaleViewJdbcDao;

/**
 * Фабрика, отдающая инстансы <code>DAO</code>.<br>
 * Используется шаблон <code>singletone</code>.
 *
 * @author Aleksei Zabezhinsky
 */
public final class DaoFactory {

    private static final DaoFactory INSTANCE = new DaoFactory();

    private final SaleDao saleDao;
    private final SaleItemDao saleItemDao;
    private final SaleViewDao saleViewDao;
    private final ProductSaleItemDao productSaleItemDao;
    private final GroupAttributeDao groupAttributeDao;
    private final AttributeItemDao attributeItemDao;
    private final ProductGroupViewDao productGroupViewDao;
    private final ProductDao productDao;

    /**
     * Дефолтный приватный конструктор.
     */
    private DaoFactory() {
        saleDao = new SaleJdbcDao();
        saleItemDao = new SaleItemJdbcDao();
        saleViewDao = new SaleViewJdbcDao();
        productSaleItemDao = new ProductSaleItemJdbcDao();
        groupAttributeDao = new GroupAttributeJdbcDao();
        productGroupViewDao = new ProductGroupViewJdbcDao();
        attributeItemDao = new AttributeItemJdbcDao();
        productDao = new ProductJdbcDao();
    }

    /**
     * Возвращает самого себя.
     * @return ServiceFactory
     */
    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Возвращает текущую реализацию {@link SaleDao}.
     * @return {@link SaleDao}
     */
    public SaleDao getSaleDao() {
        return saleDao;
    }

    /**
     * Возвращает текущую реализацию {@link SaleItemDao}.
     * @return {@link SaleItemDao}
     */
    public SaleItemDao getSaleItemDao() {
        return saleItemDao;
    }

    /**
     * Возвращает текущую реализацию {@link SaleViewDao}.
     * @return {@link SaleViewDao}
     */
    public SaleViewDao getSaleViewDao() {
        return saleViewDao;
    }

    /**
     * Возвращает текущую реализацию {@link ProductSaleItemDao}.
     * @return {@link ProductSaleItemDao}
     */
    public ProductSaleItemDao getProductSaleItemDao() {
        return productSaleItemDao;
    }

    /**
     * Возвращает текущую реализацию {@link GroupAttributeDao}.
     * @return {@link GroupAttributeDao}
     */
    public GroupAttributeDao getGroupAttributeDao() {
        return groupAttributeDao;
    }

    /**
     * Возвращает текущую реализацию {@link AttributeItemDao}.
     * @return {@link AttributeItemDao}
     */
    public AttributeItemDao getAttributeItemDao() {
        return attributeItemDao;
    }

    /**
     * Возвращает текущую реализацию {@link ProductGroupViewDao}.
     * @return {@link ProductGroupViewDao}
     */
    public ProductGroupViewDao getProductGroupViewDao() {
        return productGroupViewDao;
    }

    /**
     * Возвращает текущую реализацию {@link ProductDao}.
     * @return {@link ProductDao}
     */
    public ProductDao getProductDao() {
        return productDao;
    }

}
