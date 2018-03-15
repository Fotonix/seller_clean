package seller.entity.reference;

import java.util.List;

/**
 * Группа товаров. Данная сущность используется для вывода на <code>view</code><br>
 * Для сохранения/обновления необходимо использовать {@link ProductGroup}.
 *
 * @author Aleksei Zabezhinsky
 */
public class ProductGroupView extends ProductGroup {

    /** Признаки группы товаров. */
    private List<GroupAttributeView> groupAttributes;

    /**
     * Получает признаки группы товаров.
     *
     * @return коллекция признаков группы товаров
     */
    public List<GroupAttributeView> getGroupAttributes() {
        return groupAttributes;
    }

    /**
     * Задает признаки группы товаров.
     *
     * @param groupAttributes коллекция признаков группы товаров
     */
    public void setGroupAttributes(List<GroupAttributeView> groupAttributes) {
        this.groupAttributes = groupAttributes;
    }

}
