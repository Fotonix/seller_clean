package seller.entity.reference;

/**
 * Группа товаров.
 *
 * @author Aleksei_Zabezhinsky
 */
public class ProductGroup extends BaseReference {

    /**
     * Дефолтный конструктор.
     */
    public ProductGroup() {
        super();
    }

    /**
     * Конструктор.
     *
     * @param id ID
     * @param name наименование
     */
    public ProductGroup(Integer id, String name) {
        super(id, name);
    }

}
