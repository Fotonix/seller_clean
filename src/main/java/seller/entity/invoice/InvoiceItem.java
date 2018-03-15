package seller.entity.invoice;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Позиция приходной накладной.
 *
 * @author Aleksei Zabezhinsky
 */
public class InvoiceItem implements Serializable {

    /** Serial version. */
    private static final long serialVersionUID = 5447607034983520990L;

    /** ID. */
    private Integer id;

    /** ID приходной накладной. */
    private Integer invoiceId;

    /** ID товара. */
    private Integer productId;

    /** ID группы товара. */
    private Integer productGroupId;

    /** Количество товара. */
    private Integer quantity;

    /** Стоимость единицы товара. */
    private BigDecimal cost;

    /** Цена единицы товара. */
    private BigDecimal price;

    /**
     * Получает ID.
     *
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Задает ID.
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Получает ID приходной накладной.
     *
     * @return ID приходной накладной
     */
    public Integer getInvoiceId() {
        return invoiceId;
    }

    /**
     * Задает ID приходной накладной.
     *
     * @param invoiceId ID приходной накладной
     */
    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * Получает ID товара.
     *
     * @return ID товара
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * Задает ID товара.
     *
     * @param productId ID товара
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * Получает ID группы товара.
     *
     * @return ID группы товара
     */
    public Integer getProductGroupId() {
        return productGroupId;
    }

    /**
     * Задает ID группы товара.
     *
     * @param productGroupId ID группы товара
     */
    public void setProductGroupId(Integer productGroupId) {
        this.productGroupId = productGroupId;
    }

    /**
     * Получает количество товара.
     *
     * @return количество товара
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Задает количество товара.
     *
     * @param quantity количество товара
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Получает стоимость единицы товара.
     *
     * @return стоимость единицы товара
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Задает стоимость единицы товара.
     *
     * @param cost стоимость единицы товара
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * Получает цену единицы товара.
     *
     * @return цена единицы товара
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Задает цену единицы товара.
     *
     * @param price цена единицы товара
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
