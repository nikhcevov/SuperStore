package com.ovchingus.persistence.sqlserver.dao;

import com.ovchingus.persistence.GenericDao;
import com.ovchingus.persistence.sqlserver.entities.ProductEntitySQLServer;
import com.ovchingus.persistence.sqlserver.entities.StoreEntitySQLServer;
import com.ovchingus.persistence.sqlserver.entities.StoreProductEntitySQLServer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public abstract class ConnectionSQLServer<T, PK> implements GenericDao<T, PK> {

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
        configuration.addAnnotatedClass(StoreEntitySQLServer.class);
        configuration.addAnnotatedClass(ProductEntitySQLServer.class);
        configuration.addAnnotatedClass(StoreProductEntitySQLServer.class);
        configuration.configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

    public void openCurrentSession() {
        currentSession = getSessionFactory().openSession();
    }

    public void openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }
}
