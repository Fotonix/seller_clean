package seller.entity.reference;

/**
 * Товар. Данная сущность используется для вывода на <code>view</code><br>
 * Для сохранения/обновления необходимо использовать {@link Product}.
 *
 * @author Aleksei Zabezhinsky
 */
public class ProductView extends BaseReference {

    /** Группа товаров. */
    private String group;

    /**
     * Получает группу товаров.
     *
     * @return наименование группы товаров
     */
    public String getGroup() {
        return group;
    }

    /**
     * Задает группу товаров.
     *
     * @param group наименование группы товаров
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * Дефолтный конструктор.
     */
    public ProductView() {
        super();
    }

    /**
     * Конструктор.
     *
     * @param id ID
     * @param name наименование
     * @param group группа товаров
     */
    public ProductView(Integer id, String name, String group) {
        super(id, name);
        this.group = group;
    }

}
