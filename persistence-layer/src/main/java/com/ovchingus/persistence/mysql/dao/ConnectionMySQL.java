package com.ovchingus.persistence.mysql.dao;

import com.ovchingus.persistence.GenericDao;
import com.ovchingus.persistence.mysql.entities.ProductEntityMySQL;
import com.ovchingus.persistence.mysql.entities.StoreEntityMySQL;
import com.ovchingus.persistence.mysql.entities.StoreProductEntityMySQL;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public abstract class ConnectionMySQL<T, PK> implements GenericDao<T, PK> {

    private Session currentSession;

    private Transaction currentTransaction;

    Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(StoreEntityMySQL.class);
        configuration.addAnnotatedClass(ProductEntityMySQL.class);
        configuration.addAnnotatedClass(StoreProductEntityMySQL.class);
        configuration.configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public void openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
    }

    public void closeCurrentSession() {
        currentSession.clear();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }
}
