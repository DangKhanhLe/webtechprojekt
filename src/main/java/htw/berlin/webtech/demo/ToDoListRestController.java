package htw.berlin.webtech.demo;

import htw.berlin.webtech.demo.api.ToDoList;
import htw.berlin.webtech.demo.api.ToDoListManipulationRequest;
import htw.berlin.webtech.demo.service.ToDoListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ToDoListRestController {

    private final ToDoListService toDoListService;

    public ToDoListRestController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    @GetMapping(path = "/api/v1/todolists")
    public ResponseEntity<List<ToDoList>> fetchToDoLists() {
        return ResponseEntity.ok(toDoListService.findAll());
    }

    @GetMapping(path = "/api/v1/todolists/{id}")
    public ResponseEntity<ToDoList> fetchToDoListById(@PathVariable Long id){
        var toDoList = toDoListService.findById(id);
        return toDoList != null? ResponseEntity.ok(toDoList) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/todolists")
    public ResponseEntity<Void> createToDoLists(@RequestBody ToDoListManipulationRequest request) throws URISyntaxException {
        var toDoList = toDoListService.create(request);
        URI uri = new URI("/api/v1/todolists/" + toDoList.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/api/v1/todolists/{id}")
    public ResponseEntity<ToDoList> updateToDoList(@PathVariable Long id, @RequestBody ToDoListManipulationRequest request){
        var toDoList = toDoListService.update(id, request);
        return toDoList != null? ResponseEntity.ok(toDoList) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/todolists/{id}")
    public ResponseEntity<Void> deleteToDoList(@PathVariable Long id){
        boolean successful = toDoListService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
