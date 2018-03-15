package seller.service;

import java.util.List;

import seller.entity.reference.AttributeItem;
import seller.entity.reference.Producer;
import seller.entity.reference.Product;
import seller.entity.reference.GroupAttribute;
import seller.entity.reference.ProductGroup;
import seller.entity.reference.ProductGroupView;
import seller.entity.reference.ProductView;

/**
 * Сервис работы со справочниками.
 *
 * @author Aleksei Zabezhinsky
 */
public interface ReferenceService {

    /**
     * Получает товар по <code>id</code>.
     *
     * @param id идентификатор сущности {@link Product}
     * @return сущность {@link Product}
     */
    Product getProduct(Integer id);

    /**
     * Получает список товара по ID группы товара.
     *
     * @param groupId ID группы товара
     * @return список товара
     */
    List<Product> getProductsByGroupId(Integer groupId);

    /**
     * Создает товар.
     *
     * @param entity сущность {@link Product}
     */
    void createProduct(Product entity);

    /**
     * Обновляет товар.
     *
     * @param entity сущность {@link Product}
     */
    void updateProduct(Product entity);

    /**
     * Удаляет товары.
     *
     * @param id массив идентификаторов сущности {@link Product}
     */
    void deleteProducts(Integer[] id);

    /**
     * Получает весь товар.
     *
     * @return коллекция сущностей {@link ProductView}
     */
    List<ProductView> getProductViews();

    /**
     * Получает список групп товаров.
     *
     * @return коллекция сущностей {@link ProductGroup}
     */
    List<ProductGroup> getProductGroups();

    /**
     * Получает группу товара по <code>id</code>.
     *
     * @param id идентификатор сущности {@link ProductGroup}
     * @return сущность {@link ProductGroup}
     */
    ProductGroup getProductGroup(Integer id);

    /**
     * Создает группу товара.
     *
     * @param entity сущность {@link ProductGroup}
     */
    void createProductGroup(ProductGroup entity);

    /**
     * Обновляет группу товара.
     *
     * @param entity сущность {@link Product}
     */
    void updateProductGroup(ProductGroup entity);

    /**
     * Удаляет группы товара.
     *
     * @param id массив идентификаторов сущности {@link ProductGroup}
     */
    void deleteProductGroups(Integer[] id);

    /**
     * Получает список поставщиков.
     *
     * @return коллекция сущностей {@link Producer}
     */
    List<Producer> getProducers();

    /**
     * Получает поставщика по <code>id</code>.
     *
     * @param id идентификатор сущности {@link Producer}
     * @return сущность {@link Producer}
     */
    Producer getProducer(Integer id);

    /**
     * Создает поставщика.
     *
     * @param entity сущность {@link Producer}
     */
    void createProducer(Producer entity);

    /**
     * Обновляет поставщика.
     *
     * @param entity сущность {@link Producer}
     */
    void updateProducer(Producer entity);

    /**
     * Удаляет поставщиков.
     *
     * @param id массив идентификаторов сущности {@link Producer}
     */
    void deleteProducers(Integer[] id);

    /**
     * Получает признак группы товара по <code>id</code>.
     *
     * @param id идентификатор сущности {@link GroupAttribute}
     * @return сущность {@link GroupAttribute}
     */
    GroupAttribute getGroupAttribute(Integer id);

    /**
     * Создает признак группы товара.
     *
     * @param entity сущность {@link GroupAttribute}
     */
    void createGroupAttribute(GroupAttribute entity);

    /**
     * Обновляет признак группы товара.
     *
     * @param entity сущность {@link GroupAttribute}
     */
    void updateGroupAttribute(GroupAttribute entity);

    /**
     * Удаляет признак группы товара.
     *
     * @param id идентификатор сущности {@link GroupAttribute}
     */
    void deleteGroupAttribute(Integer id);

    /**
     * Получает позицию признака группы товара по <code>id</code>.
     *
     * @param id идентификатор сущности {@link AttributeItem}
     * @return сущность {@link AttributeItem}
     */
    AttributeItem getAttributeItem(Integer id);

    /**
     * Создает позицию признака группы товара.
     *
     * @param entity сущность {@link AttributeItem}
     */
    void createAttributeItem(AttributeItem entity);

    /**
     * Обновляет позицию признака группы товара.
     *
     * @param entity сущность {@link AttributeItem}
     */
    void updateAttributeItem(AttributeItem entity);

    /**
     * Удаляет позицию признака группы товара.
     *
     * @param id идентификатор сущности {@link GroupAttribute}
     */
    void deleteAttributeItem(Integer id);

    /**
     * Получает список групп товаров.
     *
     * @return коллекция сущностей {@link ProductGroupView}
     */
    List<ProductGroupView> getProductGroupViews();

}
