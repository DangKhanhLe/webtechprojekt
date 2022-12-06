package htw.berlin.webtech.demo.persistence;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "dueDate")
    private LocalDate dueDate;

    @Column(name = "completed", nullable = false)
    private boolean completed;

    public TaskEntity(String title, LocalDate dueDate, boolean completed) {
        this.title = title;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public TaskEntity(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    protected TaskEntity() {}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
