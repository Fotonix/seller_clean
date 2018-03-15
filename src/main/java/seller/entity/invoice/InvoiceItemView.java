package seller.entity.invoice;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Позиция приходной накладной. Данная сущность используется для вывода на <code>view</code><br>
 * Для сохранения/обновления необходимо использовать {@link InvoiceItem}.
 *
 * @author Aleksei Zabezhinsky
 */
public class InvoiceItemView {

    /** ID. */
    private int id;

    /** Группа товара. */
    private String group;

    /** Товар. */
    private String product;

    /** Количество. */
    private int quantity;

    /** Стоимость единицы товара. */
    private BigDecimal cost;

    /** Цена единицы товара. */
    private BigDecimal price;

    /**
     * Получает ID.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Задает ID.
     *
     * @param id ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получает группу товара.
     *
     * @return группа товара
     */
    public String getGroup() {
        return group;
    }

    /**
     * Задает группу товара.
     *
     * @param group группа товара
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * Получает товар.
     *
     * @return товар
     */
    public String getProduct() {
        return product;
    }

    /**
     * Задает товар.
     *
     * @param product товар
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * Получает количество.
     *
     * @return количество
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Задает количество.
     *
     * @param quantity количество
     */
    public void setQuantity(int quantity) {
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
