package htw.berlin.webtech.demo.service;

import htw.berlin.webtech.demo.api.Person;
import htw.berlin.webtech.demo.api.Task;
import htw.berlin.webtech.demo.api.TaskManipulationRequest;
import htw.berlin.webtech.demo.persistence.PersonEntity;
import htw.berlin.webtech.demo.persistence.TaskEntity;
import htw.berlin.webtech.demo.persistence.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll(){
        List<TaskEntity> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Task findById(Long id){
        var taskEntity = taskRepository.findById(id);
        return taskEntity.map(this::transformEntity).orElse(null);
    }

    public Task create(TaskManipulationRequest request){
        var taskEntity = new TaskEntity(request.getTitle(), request.getDueDate(), request.isCompleted());
        taskEntity = taskRepository.save(taskEntity);
        return transformEntity(taskEntity);
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
        taskEntity = taskRepository.save(taskEntity);

        return transformEntity(taskEntity);
    }

    public boolean deleteById(Long id){
        if(!taskRepository.existsById(id)){
            return false;
        }

        taskRepository.deleteById(id);
        return true;
    }

    private Task transformEntity(TaskEntity taskEntity){
        return new Task(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getDueDate(),
                taskEntity.isCompleted()
        );
    }

}
