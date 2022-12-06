package htw.berlin.webtech.demo.persistence;

import htw.berlin.webtech.demo.api.Task;

import javax.persistence.*;

@Entity(name = "toDoLists")
public class ToDoListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;



}
