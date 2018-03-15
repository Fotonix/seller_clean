package seller.dao;


/**
 * Описывает шаблон <code>CRUD</code> для типовых <code>DAO</code>.
 *
 * @author Aleksei_Zabezhinsky
 */
public interface CrudDao<K extends Number, E> {

    /**
     * Добавление объекта в хранилище.
     *
     * @param entity объект
     */
    void create(E entity);

    /**
     * Чтение объекта из хранилища.
     *
     * @param key ID объекта в хранилище
     * @return объект
     */
    E read(K key);

    /**
     * Обновляет объект в хранилище.
     *
     * @param entity объект
     */
    void update(E entity);

    /**
     * Удаляет объект из хранилища
     *
     * @param key ID объекта в хранилище
     */
    void delete(K key);

}
