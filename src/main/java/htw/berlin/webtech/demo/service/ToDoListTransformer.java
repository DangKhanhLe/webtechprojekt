package htw.berlin.webtech.demo.service;

import htw.berlin.webtech.demo.api.ToDoList;
import htw.berlin.webtech.demo.persistence.ToDoListEntity;
import org.springframework.stereotype.Service;

@Service
public class ToDoListTransformer {

    public ToDoList transformEntity(ToDoListEntity toDoListEntity){
        return new ToDoList(
                toDoListEntity.getId(),
                toDoListEntity.getTitle(),
                toDoListEntity.getDescription()
        );
    }

}
