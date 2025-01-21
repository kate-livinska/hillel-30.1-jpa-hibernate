package app.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "homework")
@Table(name = "t_homeworks")
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDate deadline;
    private int mark;

    public Homework() {
        this.id = null;
        this.description = null;
        this.deadline = LocalDate.now();
        this.mark = 0;
    }

    @ManyToOne
    private Student student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Homework homework = (Homework) o;
        return getMark() == homework.getMark() && Objects.equals(getId(), homework.getId()) && Objects.equals(getDescription(), homework.getDescription()) && Objects.equals(getDeadline(), homework.getDeadline()) && Objects.equals(getStudent(), homework.getStudent());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", mark=" + mark +
                '}';
    }
}
