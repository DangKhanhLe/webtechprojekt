package htw.berlin.webtech.demo.api;

import java.time.LocalDate;

public class TaskManipulationRequest {

    private String title;
    private LocalDate dueDate;
    private boolean completed;
    private Long toDoListId;

    public TaskManipulationRequest(String title, LocalDate dueDate, boolean completed, Long toDoListId) {
        this.title = title;
        this.dueDate = dueDate;
        this.completed = completed;
        this.toDoListId = toDoListId;
    }

    public TaskManipulationRequest() {}

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
