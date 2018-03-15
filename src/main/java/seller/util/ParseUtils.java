package seller.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Утилитный класс. Осуществляет преобразование типов данных.
 *
 * @author Aleksei Zabezhinsky
 */
public final class ParseUtils {

    /** Дефолтный конструктор. */
    private ParseUtils() {
    }

    /**
     * Преобразовывает <code>String</code> в <code>Integer</code>.<br />
     * Если строка равна <code>null</code>, нулевой длины или состоит из одних пробелов -
     * возвращает <code>null</code>.
     *
     * @param param строка для преобразования
     * @return <code>Integer</code> из строки или null
     * @exception NumberFormatException если строка не может быть преобразована в <code>Integer</code>
     */
    public static Integer stringToInteger(String param) throws NumberFormatException {
        Integer result = null;
        if (param != null) {
            String trimParam = param.trim();
            if (!trimParam.isEmpty()) {
                result = Integer.valueOf(trimParam);
            }
        }
        return result;
    }

    /**
     * Преобразовывает <code>String</code> в <code>BigDecimal</code>.<br />
     * Если строка равна <code>null</code>, нулевой длины или состоит из одних пробелов -
     * возвращает <code>null</code>.
     *
     * @param param строка для преобразования
     * @return <code>BigDecimal</code> из строки или null
     * @exception NumberFormatException если строка не может быть преобразована в <code>BigDecimal</code>
     */
    public static BigDecimal stringToBigDecimal(String param) throws NumberFormatException {
        BigDecimal result = null;
        if (param != null) {
            String trimParam = param.trim();
            if (!trimParam.isEmpty()) {
                result = new BigDecimal(trimParam);
            }
        }
        return result;
    }

    /**
     * Преобразовывает <code>String</code> в <code>Date</code>.<br />
     * Если строка равна <code>null</code>, нулевой длины или состоит из одних пробелов -
     * возвращает <code>null</code>.
     *
     * @param param строка для преобразования
     * @param format шаблон преобразования строки
     * @return <code>Date</code> из строки или null
     */
    public static Date stringToDate(String param, final String format) {
        Date date = null;
        String trimParam = param == null ? null : param.trim();
        if (trimParam != null && !trimParam.isEmpty()) {
            try {
                date = new SimpleDateFormat(format).parse(param.trim());
            } catch (ParseException e) {
                // Возникает при ошибке преобразования строки в дату.
                throw new RuntimeException("Error parsing date: \"" + param + "\"", e);
            }
        }
        return date;
    }

}
