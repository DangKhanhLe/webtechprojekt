package htw.berlin.webtech.demo.api;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Task {

    private long id;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
    private boolean completed;
    private Long toDoListId;


    public Task(long id, String title, LocalDate dueDate, boolean completed, Long toDoListId) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.completed = completed;
        this.toDoListId = toDoListId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Long getToDoListId() {
        return toDoListId;
    }

    public void setToDoListId(Long toDoListId) {
        this.toDoListId = toDoListId;
    }
}
