package seller.entity.sale;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Реализация конкретного товара.
 *
 * @author Aleksei Zabezhinsky
 */
public class SaleItem {

    /** ID. */
    private Integer id;

    /** ID реализации товара. */
    private Integer saleId;

    /** ID товара. */
    private Integer productId;

    /** Количество товара. */
    private Integer quantity;

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
     * Получает ID реализации товара.
     *
     * @return ID реализации товара
     */
    public Integer getSaleId() {
        return saleId;
    }

    /**
     * Задает ID реализации товара.
     *
     * @param saleId ID реализации товара
     */
    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
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
