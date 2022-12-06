package htw.berlin.webtech.demo.api;

import java.util.List;

public class ToDoList {

    private Long id;
    private Task task;

    public ToDoList(Long id, Task task) {
        this.id = id;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
