package htw.berlin.webtech.demo.service;

import htw.berlin.webtech.demo.api.Task;
import htw.berlin.webtech.demo.persistence.TaskEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskTransformer {
    public Task transformEntity(TaskEntity taskEntity){
        var toDoListId = taskEntity.getToDoListEntity() != null ? taskEntity.getToDoListEntity().getId() : null;
        return new Task(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getDueDate(),
                taskEntity.isCompleted(),
                toDoListId
        );
    }
}
