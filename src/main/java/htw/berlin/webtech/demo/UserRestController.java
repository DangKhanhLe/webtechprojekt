package htw.berlin.webtech.demo;

import htw.berlin.webtech.demo.api.User;
import htw.berlin.webtech.demo.api.UserManipulationRequest;
import htw.berlin.webtech.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/api/v1/users")
    public ResponseEntity<List<User>> fetchUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(path = "/api/v1/users/{id}")
    public ResponseEntity<User> fetchUserById(@PathVariable Long id){
        var user = userService.findById(id);
        return user != null? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/users")
    public ResponseEntity<Void> createUser(@RequestBody UserManipulationRequest request) throws URISyntaxException {
        var user = userService.create(request);
        URI uri = new URI("/api/v1/users/" + user.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/api/v1/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserManipulationRequest request){
        var user = userService.update(id, request);
        return user != null? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        boolean successful = userService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
