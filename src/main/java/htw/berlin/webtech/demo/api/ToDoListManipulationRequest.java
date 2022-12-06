package htw.berlin.webtech.demo.api;

import java.util.List;

public class ToDoListManipulationRequest {
    private Task task;

    public ToDoListManipulationRequest(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
