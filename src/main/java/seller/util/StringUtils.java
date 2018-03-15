package seller.util;

/**
 * Утилитный класс. Содержит методы для работы со строками.
 *
 * @author Aleksei Zabezhinsky
 */
public final class StringUtils {

    /** Дефолтный конструктор. */
    private StringUtils() {
    }

    /**
     * Возвращает строку, с удаленными пробелами слева и справа.<br />
     * Если строка равна <code>null</code> или после обрезания пробелов нулевой длины -
     * возвращает <code>null</code>.
     *
     * @param param строка для преобразования
     * @return результирующая строка
     */
    public static String normalize(String param) {
        String result = null;
        if (param != null) {
            String trimParam = param.trim();
            if (!trimParam.isEmpty()) {
                result = trimParam;
            }
        }
        return result;
    }

}
