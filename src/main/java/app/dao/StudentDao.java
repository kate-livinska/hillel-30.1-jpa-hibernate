package app.dao;

import app.model.Student;
import jakarta.persistence.*;

import java.util.List;

public class StudentDao implements GenericDao<Student, Long> {
    private final EntityManagerFactory entityManagerFactory;

    public StudentDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(Student entity) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public Student findById(Long aLong) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.find(Student.class, aLong);
        }
    }

    @Override
    public Student findByEmail(String email) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM student s WHERE s.email = :email", Student.class);
            query.setParameter("email", email);
            query.setMaxResults(1);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Student> findAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("select s from student s", Student.class).getResultList();
        }
    }

    @Override
    public Student update(Student entity) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Student updated = entityManager.merge(entity);
            entityManager.getTransaction().commit();
            return updated;
        }
    }

    @Override
    public boolean deleteById(Long aLong) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Student.class, aLong));
            entityManager.getTransaction().commit();

            if (entityManager.find(Student.class, aLong) == null) {
                return true;
            }
        }
        return false;
    }
}
