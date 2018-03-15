package seller.entity.reference;

/**
 * Поставщик.
 *
 * @author Aleksei_Zabezhinsky
 */
public class Producer extends BaseReference {

    /**
     * Дефолтный конструктор.
     */
    public Producer() {
        super();
    }

    /**
     * Конструктор.
     *
     * @param id ID
     * @param name наименование
     */
    public Producer(Integer id, String name) {
        super(id, name);
    }

}
