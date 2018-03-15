package seller.entity.reference;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Базовая сущность для справочников.
 *
 * @author Aleksei_Zabezhinsky
 */
public abstract class BaseReference {

    /** ID. */
    private Integer id;

    /** Наименование. */
    private String name;

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
     * Получает наименование.
     *
     * @return наименование
     */
    public String getName() {
        return name;
    }

    /**
     * Задает наименование.
     *
     * @param name наименование
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Дефолтный конструктор.
     */
    public BaseReference() {
        super();
    }

    /**
     * Конструктор.
     *
     * @param id ID
     * @param name наименование
     */
    public BaseReference(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
