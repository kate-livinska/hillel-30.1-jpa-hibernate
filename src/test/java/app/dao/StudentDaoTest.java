package app.dao;

import app.model.Homework;
import app.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StudentDaoTest extends DaoTestParent {
    private StudentDao studentDao;

    @BeforeEach
    void setUp() {
        studentDao = new StudentDao(entityManagerFactory);
    }

    @Test
    void findByEmail_studentWithEmailExists() {
        String testEmail = "john.doe@example.com";
        Student testStudent = studentDao.findByEmail(testEmail);

        assertNotNull(testStudent);
        assertEquals(testEmail, testStudent.getEmail());
    }

    @Test
    void findByEmail_studentWithEmailDoesNotExistReturnsNull() {
        String testEmail = "noemail@example.com";
        Student testStudent = studentDao.findByEmail(testEmail);
        assertNull(testStudent);
    }

    @Test
    void saveTest_studentWithoutHomework() {
        Student testStudent = new Student();
        testStudent.setFirstName("Test");
        testStudent.setLastName("Test");
        testStudent.setEmail("test@test.com");

        studentDao.save(testStudent);
        Student savedStudent = studentDao.findByEmail(testStudent.getEmail());

        assertNotNull(savedStudent);
        assertEquals(testStudent.getFirstName(), savedStudent.getFirstName());
        assertEquals(testStudent.getLastName(), savedStudent.getLastName());
    }

    @Test
    void saveTest_studentWithHomework() {
        Student testStudent = new Student();
        testStudent.setFirstName("Test");
        testStudent.setLastName("Test");
        testStudent.setEmail("test@test.com");

        Homework testHomework = new Homework();
        testHomework.setDescription("Test homework");
        testHomework.setStudent(testStudent);

        testStudent.setHomeworks(Set.of(testHomework));

        studentDao.save(testStudent);

        Student savedStudent = studentDao.findByEmail(testStudent.getEmail());
        assertNotNull(savedStudent);
        assertNotNull(savedStudent.getHomeworks());
        assertEquals(testStudent.getHomeworks(), savedStudent.getHomeworks());
        savedStudent.getHomeworks().forEach(homework -> assertNotNull(homework.getId()));
    }

    @Test
    void findById_studentExists() {
        Long testId = 1L;
        Student testStudent = studentDao.findById(testId);

        assertNotNull(testStudent);
        assertEquals(testId, testStudent.getId());
        assertNotNull(testStudent.getHomeworks());
    }

    @Test
    void findById_studentDoesNotExistReturnsNull() {
        Long testId = 100000L;
        Student testStudent = studentDao.findById(testId);
        assertNull(testStudent);
    }



    @Test
    void findAll() {
        List<Student> students = studentDao.findAll();
        assertNotNull(students);
        assertTrue(students.size() == 5); //not sure if this is a valid check, as it's based on the actual number of entries in the init.sql
    }

    @Test
    void update() {
        Student testStudent = studentDao.findByEmail("john.doe@example.com");
        testStudent.setFirstName("JohnJohn");

        assertEquals(testStudent.getFirstName(), "JohnJohn");
    }

    @Test
    void deleteById() {
        boolean wasDeleted = studentDao.deleteById(1L);
        Student deletedStudent = studentDao.findByEmail("john.doe@example.com");

        assertTrue(wasDeleted);
        assertNull(deletedStudent);
    }
}