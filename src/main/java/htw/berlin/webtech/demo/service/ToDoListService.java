package htw.berlin.webtech.demo.service;

import htw.berlin.webtech.demo.api.ToDoList;
import htw.berlin.webtech.demo.api.ToDoListManipulationRequest;
import htw.berlin.webtech.demo.persistence.ToDoListEntity;
import htw.berlin.webtech.demo.persistence.ToDoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoListService {

    private final ToDoListRepository toDoListRepository;
    private final ToDoListTransformer toDoListTransformer;

    public ToDoListService(ToDoListRepository toDoListRepository, ToDoListTransformer toDoListTransformer) {
        this.toDoListRepository = toDoListRepository;
        this.toDoListTransformer = toDoListTransformer;
    }

    public List<ToDoList> findAll() {
        List<ToDoListEntity> toDoLists = toDoListRepository.findAll();
        return toDoLists.stream()
                .map(toDoListTransformer::transformEntity)
                .collect(Collectors.toList());
    }

    public ToDoList findById(Long id){
        var toDoListEntity = toDoListRepository.findById(id);
        return toDoListEntity.map(toDoListTransformer::transformEntity).orElse(null);
    }

    public ToDoList create(ToDoListManipulationRequest request){
        var toDoListEntity = new ToDoListEntity(request.getTitle(), request.getDescription());
        toDoListEntity = toDoListRepository.save(toDoListEntity);
        return toDoListTransformer.transformEntity(toDoListEntity);
    }

    public ToDoList update(Long id, ToDoListManipulationRequest request){
        var toDoListEntityOptional = toDoListRepository.findById(id);
        if(toDoListEntityOptional.isEmpty()){
            return null;
        }

        var toDoListEntity = toDoListEntityOptional.get();
        toDoListEntity.setTitle(request.getTitle());
        toDoListEntity.setDescription(request.getDescription());
        toDoListEntity = toDoListRepository.save(toDoListEntity);

        return toDoListTransformer.transformEntity(toDoListEntity);
    }

    public boolean deleteById(Long id){
        if(!toDoListRepository.existsById(id)){
            return false;
        }

        toDoListRepository.deleteById(id);
        return true;
    }

}
