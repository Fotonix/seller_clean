package seller.entity.reference;

/**
 * Позиция признака группы товаров.
 *
 * @author Aleksei_Zabezhinsky
 */
public class AttributeItem extends BaseReference {

    private Integer attributeId;

    /**
     * Получает ID признака группы товаров.
     *
     * @return ID признака группы товаров
     */
    public Integer getAttributeId() {
        return attributeId;
    }

    /**
     * Задает ID признака группы товаров.
     *
     * @param attributeId ID признака группы товаров
     */
    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    /**
     * Дефолтный конструктор.
     */
    public AttributeItem() {
        super();
    }

    /**
     * Конструктор.
     *
     * @param id ID
     * @param name наименование
     * @param attributeId ID признака группы товаров
     */
    public AttributeItem(Integer id, String name, Integer attributeId) {
        super(id, name);
        this.attributeId = attributeId;
    }

}
