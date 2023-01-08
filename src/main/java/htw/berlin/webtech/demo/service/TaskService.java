package htw.berlin.webtech.demo.service;

import htw.berlin.webtech.demo.api.Task;
import htw.berlin.webtech.demo.api.TaskManipulationRequest;
import htw.berlin.webtech.demo.persistence.TaskEntity;
import htw.berlin.webtech.demo.persistence.TaskRepository;
import htw.berlin.webtech.demo.persistence.ToDoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ToDoListRepository toDoListRepository;
    private final TaskTransformer taskTransformer;

    public TaskService(TaskRepository taskRepository, ToDoListRepository toDoListRepository, TaskTransformer taskTransformer) {
        this.taskRepository = taskRepository;
        this.toDoListRepository = toDoListRepository;
        this.taskTransformer = taskTransformer;
    }

    public List<Task> findAll(){
        List<TaskEntity> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(taskTransformer::transformEntity)
                .collect(Collectors.toList());
    }

    public Task findById(Long id){
        var taskEntity = taskRepository.findById(id);
        return taskEntity.map(taskTransformer::transformEntity).orElse(null);
    }

    public Task create(TaskManipulationRequest request){
        var toDoList = request.getToDoListId() != null ? toDoListRepository.findById(request.getToDoListId()).orElseThrow() : null;
        var taskEntity = new TaskEntity(request.getTitle(), request.getDueDate(), request.isCompleted(), toDoList);
        taskEntity = taskRepository.save(taskEntity);
        return taskTransformer.transformEntity(taskEntity);
    }

    public Task update(Long id, TaskManipulationRequest request){
        var taskEntityOptional = taskRepository.findById(id);
        if(taskEntityOptional.isEmpty()){
            return null;
        }

        var taskEntity = taskEntityOptional.get();
        taskEntity.setTitle(request.getTitle());
        taskEntity.setDueDate(request.getDueDate());
        taskEntity.setCompleted(request.isCompleted());

        var toDoListId = request.getToDoListId() != null ? request.getToDoListId() : null;
        if (toDoListId != null){
            taskEntity.setToDoListEntity(toDoListRepository.findById(toDoListId).orElseThrow());
        } else {
            taskEntity.setToDoListEntity(null);
        }

        taskEntity = taskRepository.save(taskEntity);

        return taskTransformer.transformEntity(taskEntity);
    }

    public boolean deleteById(Long id){
        if(!taskRepository.existsById(id)){
            return false;
        }

        taskRepository.deleteById(id);
        return true;
    }

}
