package seller.entity.sale;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import seller.entity.reference.Product;

/**
 * Реализация в разрезе товара.
 *
 * @author Aleksei Zabezhinsky
 */
public class ProductSaleItem {

    /** Товар. */
    private Product product;

    /** Остаток. */
    private int balance;

    /** Реализации товара. */
    private List<SaleItem> items;

    /**
     * Получает товар.
     *
     * @return товар
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Задает товар.
     *
     * @param product товар
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Получает остаток.
     *
     * @return остаток
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Задает остаток.
     *
     * @param balance остаток
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Получает список реализаций товара.
     *
     * @return реализации товара
     */
    public List<SaleItem> getItems() {
        return items;
    }

    /**
     * Задает список реализаций товара.
     *
     * @param items список реализаций товара
     */
    public void setItems(List<SaleItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
