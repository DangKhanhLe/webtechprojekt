package htw.berlin.webtech.demo.api;

import java.time.LocalDate;

public class TaskManipulationRequest {

    private String title;
    private LocalDate dueDate;
    private boolean completed;

    public TaskManipulationRequest(String title, LocalDate dueDate, boolean completed) {
        this.title = title;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public TaskManipulationRequest(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
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
