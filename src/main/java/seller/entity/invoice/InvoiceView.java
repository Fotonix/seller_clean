package seller.entity.invoice;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Приходная накладная. Данная сущность используется для вывода на <code>view</code><br>
 * Для сохранения/обновления необходимо использовать {@link Invoice}.
 *
 * @author Aleksei Zabezhinsky
 */
public class InvoiceView {

    /** ID. */
    private int id;

    /** Дата накладной */
    private Date date;

    /** Серия накладной */
    private String series;

    /** Поставщик */
    private String producer;

    /** Признак проведения */
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
     * Получает поставщика.
     *
     * @return поставщик
     */
    public String getProducer() {
        return producer;
    }

    /**
     * Задает поставщика.
     *
     * @param producer поставщик
     */
    public void setProducer(String producer) {
        this.producer = producer;
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
