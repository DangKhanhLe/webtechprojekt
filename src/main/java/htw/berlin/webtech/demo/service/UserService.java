package htw.berlin.webtech.demo.service;

import htw.berlin.webtech.demo.api.Task;
import htw.berlin.webtech.demo.api.TaskManipulationRequest;
import htw.berlin.webtech.demo.api.User;
import htw.berlin.webtech.demo.api.UserManipulationRequest;
import htw.berlin.webtech.demo.persistence.TaskEntity;
import htw.berlin.webtech.demo.persistence.UserEntity;
import htw.berlin.webtech.demo.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public List<User> findAll(){
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public User findById(Long id){
        var userEntity = userRepository.findById(id);
        return userEntity.map(this::transformEntity).orElse(null);
    }

    public User create(UserManipulationRequest request){
        var userEntity = new UserEntity(request.getUsername(), request.getEmail(), request.getPassword());
        userEntity = userRepository.save(userEntity);
        return transformEntity(userEntity);
    }

    public User update(Long id, UserManipulationRequest request){
        var userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.isEmpty()){
            return null;
        }

        var userEntity = userEntityOptional.get();
        userEntity.setUsername(request.getUsername());
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(request.getPassword());
        userEntity = userRepository.save(userEntity);

        return transformEntity(userEntity);
    }

    public boolean deleteById(Long id){
        if(!userRepository.existsById(id)){
            return false;
        }

        userRepository.deleteById(id);
        return true;
    }

    private User transformEntity(UserEntity userEntity){
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPassword()
        );
    }
}
