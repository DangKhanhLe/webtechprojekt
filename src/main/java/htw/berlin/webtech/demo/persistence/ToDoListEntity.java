package htw.berlin.webtech.demo.persistence;

import htw.berlin.webtech.demo.api.Task;
import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.HashSet;

@Entity(name = "toDoLists")
public class ToDoListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Description", nullable = false)
    private String description;

//    @OneToMany
//    @JoinColumn(name = task_id)
//    private Set<Task> = new HashSet<Task>();

    public ToDoListEntity(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public ToDoListEntity() {}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
