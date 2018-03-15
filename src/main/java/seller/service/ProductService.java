package seller.service;

/**
 * Сервис работы с продукцией.
 *
 * @author Aleksei Zabezhinsky
 */
public interface ProductService {

    /**
     * Удаляет товары.
     *
     * @param id массив идентификаторов сущности {@link seller.entity.reference.Product}
     */
    void deleteProducts(Integer[] id);

}
