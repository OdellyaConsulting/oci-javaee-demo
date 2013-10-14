package com.odellya.oci.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Martial SOMDA
 */
public abstract class AbstractEntityManagerTest {

    protected static Logger LOGGER = LoggerFactory.getLogger(AbstractEntityManagerTest.class);
    
    private static EntityManagerFactory emf;
    private EntityManager entityManager;
    private static Connection connection;

    @BeforeClass
    public static void setupEMF() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:unit-testing-jpa");
        } catch (Exception ex) {
            LOGGER.error("Erreur lors de l'initialisation de l'unité de persistance", ex);
        }
        
        emf = Persistence.createEntityManagerFactory("demo-pu-test");
    }

    @Before
    public void setupEntityManager() {
        try {
            entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();
        } catch (Throwable e) {
            // Because ignoring the exception is so sensible; thanks JUnit.
            e.printStackTrace();
        }
    }

    @After
    public void tearDownEntityManager() {
        try {
            if (entityManager.isOpen()) {
                if (entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                }

                entityManager.close();
                connection.createStatement().execute("SHUTDOWN");
            }
        } catch (Throwable e) {
            // Because overriding the test exception with this one is so sensible;
            // thanks again JUnit.
            e.printStackTrace();
        }
    }

    public final EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public final EntityManager getEntityManager() {
        return entityManager;
    }

    public final void persistAndFlush(Object... entities) {
        for (Object entity : entities) {
            getEntityManager().persist(entity);
        }

        getEntityManager().flush();
    }
}
