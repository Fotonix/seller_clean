package seller.entity.invoice;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Приходная накладная.
 *
 * @author Aleksei Zabezhinsky
 */
public class Invoice {

    /** ID. */
    private int id;

    /** Дата накладной. */
    private Date date;

    /** Серия накладной. */
    private String series;

    /** ID поставщика. */
    private Integer producerId;

    /** Признак проведения. */
    private boolean held;

    /**
     * Получает ID.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Задает ID.
     *
     * @param id ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получает дату накладной.
     *
     * @return дата накладной
     */
    public Date getDate() {
        return date;
    }

    /**
     * Задает дату накладной.
     *
     * @param date дата накладной
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Получает серию накладной.
     *
     * @return серия накладной
     */
    public String getSeries() {
        return series;
    }

    /**
     * Задает серию накладной.
     *
     * @param series серия накладной
     */
    public void setSeries(String series) {
        this.series = series;
    }

    /**
     * Получает ID поставщика.
     *
     * @return ID поставщика
     */
    public Integer getProducerId() {
        return producerId;
    }

    /**
     * Задает ID поставщика.
     *
     * @param producerId ID поставщика
     */
    public void setProducerId(Integer producerId) {
        this.producerId = producerId;
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
