package seller.entity.sale;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Реализация товара.
 *
 * @author Aleksei Zabezhinsky
 */
public class Sale {

    /** ID. */
    private Integer id;

    /** Дата реализации. */
    private Date date;

    /** Признак проведения. */
    private boolean held;

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
     * Получает дату реализации.
     *
     * @return дата реализации
     */
    public Date getDate() {
        return date;
    }

    /**
     * Задает дату реализации.
     *
     * @param date дата реализации
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Получает признак проведения.
     *
     * @return признак проведения
     */
    public boolean isHeld() {
        return held;
    }

    /**
     * Задает признак проведения.
     *
     * @param held признак проведения
     */
    public void setHeld(boolean held) {
        this.held = held;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
