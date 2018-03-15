package seller.entity.reference;

import java.math.BigDecimal;

/**
 * Товар.
 *
 * @author Aleksei Zabezhinsky
 */
public class Product extends BaseReference {

    /** ID группы товаров. */
    private Integer groupId;

    /** Цена единицы товара. */
    private BigDecimal price;

    /**
     * Получает ID группы товаров.
     *
     * @return ID группы товаров
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * Задает ID группы товаров.
     *
     * @param groupId ID группы товаров
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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

    /**
     * Дефолтный конструктор.
     */
    public Product() {
        super();
    }

    /**
     * Конструктор.
     *
     * @param id ID
     * @param name наименование
     * @param groupId группа товаров
     */
    public Product(Integer id, String name, Integer groupId) {
        super(id, name);
        this.groupId = groupId;
    }

}
