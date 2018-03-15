package seller.entity.reference;

/**
 * Признак группы товаров.
 *
 * @author Aleksei_Zabezhinsky
 */
public class GroupAttribute extends BaseReference {

    /** ID группы товаров. */
    private Integer groupId;

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
     * Дефолтный конструктор.
     */
    public GroupAttribute() {
        super();
    }

    /**
     * Конструктор.
     *
     * @param id ID
     * @param name наименование
     */
    public GroupAttribute(Integer id, String name) {
        super(id, name);
    }

}
