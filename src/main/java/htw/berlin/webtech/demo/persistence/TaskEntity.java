package htw.berlin.webtech.demo.persistence;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "DueDate", nullable = false)
    private LocalDate dueDate;

    @Column(name = "Completed", nullable = false)
    private boolean completed;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "toDoList_id", referencedColumnName = "Id")
    private ToDoListEntity toDoListEntity;

    public TaskEntity(String title, LocalDate dueDate, boolean completed, ToDoListEntity toDoListEntity) {
        this.title = title;
        this.dueDate = dueDate;
        this.completed = completed;
        this.toDoListEntity = toDoListEntity;
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

    public ToDoListEntity getToDoListEntity() {
        return toDoListEntity;
    }

    public void setToDoListEntity(ToDoListEntity toDoListEntity) {
        this.toDoListEntity = toDoListEntity;
    }
}
