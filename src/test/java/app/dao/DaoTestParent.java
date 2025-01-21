package app.dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class DaoTestParent {
    protected EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUpParent() {
        entityManagerFactory = Persistence.createEntityManagerFactory("test-pu");
    }

    @AfterEach
    void tearDownParent() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
