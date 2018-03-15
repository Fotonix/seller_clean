package seller.service;

import java.util.List;

import seller.entity.invoice.Invoice;
import seller.entity.invoice.InvoiceItem;
import seller.entity.invoice.InvoiceItemView;
import seller.entity.invoice.InvoiceView;

/**
 * Сервис работы с поступлением товара.
 *
 * @author Aleksei Zabezhinsky
 */
public interface InvoiceService {

    /**
     * Получает все приходные накладные.
     *
     * @return коллекция сущностей {@link InvoiceView}
     */
    List<InvoiceView> getInvoiceViews();

    /**
     * Получает приходную накладную по <code>id</code>.
     *
     * @param id идентификатор сущности {@link Invoice}
     * @return сущность {@link Invoice}
     */
    Invoice getInvoice(Integer id);

    /**
     * Удаляет приходные накладные.
     *
     * @param id массив идентификаторов сущности {@link Invoice}
     */
    void deleteInvoices(Integer[] id);

    /**
     * Создает приходную накладную.
     *
     * @param entity сущность {@link Invoice}
     */
    void createInvoice(Invoice entity);

    /**
     * Обновляет приходную накладную.
     *
     * @param entity сущность {@link Invoice}
     */
    void updateInvoice(Invoice entity);

    /**
     * Получает все позиции приходной накладной по ее ID.
     *
     * @param invoiceId ID приходной накладной
     * @return коллекция сущностей {@link InvoiceItemView}
     */
    List<InvoiceItemView> getInvoiceItemViews(Integer invoiceId);

    /**
     * Удаляет позиции приходной накладной.
     *
     * @param id массив идентификаторов сущности {@link seller.entity.invoice.InvoiceItem}
     */
    void deleteInvoiceItems(Integer[] id);

    /**
     * Получает позицию приходной накладной по <code>id</code>.
     *
     * @param id идентификатор сущности {@link InvoiceItem}
     * @return сущность {@link InvoiceItem}
     */
    InvoiceItem getInvoiceItem(Integer id);

    /**
     * Создает позицию приходной накладной.
     *
     * @param entity сущность {@link InvoiceItem}
     */
    void createInvoiceItem(InvoiceItem entity);

    /**
     * Обновляет позицию приходной накладной.
     *
     * @param entity сущность {@link InvoiceItem}
     */
    void updateInvoiceItem(InvoiceItem entity);

}
