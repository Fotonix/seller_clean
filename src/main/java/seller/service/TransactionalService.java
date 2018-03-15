package seller.service;

import seller.jdbc.TransactionUtil;

/**
 * Позволяет управлять транзакциями на уровне сервиса.
 *
 * @author Aleksei Zabezhinsky
 */
public abstract class TransactionalService {

    /** Start transaction. */
    public void startTransaction() {
        TransactionUtil.startTransaction();
    }

    /** Commit. */
    public void commitTransaction() {
        TransactionUtil.commitTransaction();
    }

    /** Rollback. */
    public void rollbackTransaction() {
        TransactionUtil.rollbackTransaction();
    }
}
