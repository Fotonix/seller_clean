package seller.entity.reference;

import java.util.List;

/**
 * Признак группы товаров. Данная сущность используется для вывода на <code>view</code><br>
 * Для сохранения/обновления необходимо использовать {@link GroupAttribute}.
 *
 * @author Aleksei Zabezhinsky
 */
public class GroupAttributeView extends GroupAttribute {

    /** Позиции признаков группы товаров. */
    private List<AttributeItem> attributeItems;

    /**
     * Получает позиции признаков группы товаров.
     *
     * @return коллекция позиций признаков группы товаров
     */
    public List<AttributeItem> getAttributeItems() {
        return attributeItems;
    }

    /**
     * Задает позиции признаков группы товаров.
     *
     * @param attributeItems коллекция позиций признаков группы товаров
     */
    public void setAttributeItems(List<AttributeItem> attributeItems) {
        this.attributeItems = attributeItems;
    }

}
