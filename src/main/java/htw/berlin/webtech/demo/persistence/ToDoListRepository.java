package htw.berlin.webtech.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoListEntity, Long> {

    List<ToDoListEntity> findAllByTitle (String title);
}
