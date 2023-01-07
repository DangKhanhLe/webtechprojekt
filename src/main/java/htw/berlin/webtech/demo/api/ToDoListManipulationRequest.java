package htw.berlin.webtech.demo.api;

import java.util.List;

public class ToDoListManipulationRequest {

    private String title;
    private String description;

    public ToDoListManipulationRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public ToDoListManipulationRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
