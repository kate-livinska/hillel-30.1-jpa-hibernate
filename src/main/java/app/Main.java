package app;

import app.dao.StudentDao;
import app.model.Homework;
import app.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, String> config = new HashMap<>();
        config.put("javax.persistence.jdbc.url", String.format("jdbc:mysql://%s/%s?allowPublicKeyRetrieval=true&useSSL=false", System.getenv("DB_HOST"), System.getenv("DB_NAME")));
        config.put("javax.persistence.jdbc.user", System.getenv("DB_USER"));
        config.put("javax.persistence.jdbc.password", System.getenv("DB_PASSWORD"));

        try (
                EntityManagerFactory entityManagerFactory
                        = Persistence.createEntityManagerFactory("hillel-persistence-unit", config);
        ) {
            StudentDao studentDao = new StudentDao(entityManagerFactory);

            Student foundStudent = studentDao.findById(1L);

            boolean wasDeleted = studentDao.deleteById(5L);
            if (wasDeleted) {
                System.out.println("Student deleted.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
