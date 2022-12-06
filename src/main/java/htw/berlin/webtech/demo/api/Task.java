package htw.berlin.webtech.demo.api;

import java.time.LocalDate;

public class Task {

    private long id;
    private String title;
    private LocalDate dueDate;
    private boolean completed;

    public Task(long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public Task(long id, String title, LocalDate dueDate, boolean completed) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.completed = completed;
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
}
