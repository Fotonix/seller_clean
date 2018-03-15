package seller.web.servlet;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Aleksei Zabezhinsky
 *
 */
public abstract class BaseForm<T> {

    public abstract void initForm(T entity);

    //public abstract void parseRequest(, T entity);

    public abstract void bindRequest(HttpServletRequest request);

}
