package seller.service;

/**
 * Фабрика, отдающая инстансы сервисов.<br>
 * Используется шаблон <code>singletone</code>.
 *
 * @author Aleksei Zabezhinsky
 */
public final class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final ReferenceService referenceService;

    private final InvoiceService invoiceService;

    private final SaleService saleService;

    private final ProductService productService;

    /**
     * Дефолтный приватный конструктор.
     */
    private ServiceFactory() {
        referenceService = new ReferenceServiceJdbcImpl();
        invoiceService = new InvoiceServiceJdbcImpl();
        saleService = new SaleServiceImpl();
        productService = new ProductServiceJdbcImpl();
    }

    /**
     * Возвращает самого себя.
     * @return ServiceFactory
     */
    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Возвращает текущую реализацию {@link ReferenceService}.
     * @return {@link ReferenceService}
     */
    public ReferenceService getReverenceService() {
        return referenceService;
    }

    /**
     * Возвращает текущую реализацию {@link InvoiceService}.
     * @return {@link InvoiceService}
     */
    public InvoiceService getInvoiceService() {
        return invoiceService;
    }

    /**
     * Возвращает текущую реализацию {@link SaleService}.
     * @return {@link SaleService}
     */
    public SaleService getSaleService() {
        return saleService;
    }

    /**
     * Возвращает текущую реализацию {@link ProductService}.
     * @return {@link ProductService}
     */
    public ProductService getProductService() {
        return productService;
    }
}
