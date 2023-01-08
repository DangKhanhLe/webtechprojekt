package htw.berlin.webtech.demo.web;

import htw.berlin.webtech.demo.ToDoListRestController;
import htw.berlin.webtech.demo.api.ToDoList;
import htw.berlin.webtech.demo.service.ToDoListService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ToDoListRestController.class)
public class ToDoListRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoListService toDoListService;

    @Test
    @DisplayName("should return found todolist from person service")
    void should_return_found_todolist_from_person_service() throws Exception {
        // given
        var toDoLists = List.of(
                new ToDoList(1L, "ToDoList1", "Number1"),
                new ToDoList(2L, "ToDoList2", "Number2")
        );
        doReturn(toDoLists).when(toDoListService).findAll();

        // when
        mockMvc.perform(get("/api/v1/todolists"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("ToDoList1"))
                .andExpect(jsonPath("$[0].description").value("Number1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("ToDoList2"))
                .andExpect(jsonPath("$[1].description").value("Number2"));
    }

    @Test
    @DisplayName("should return 404 if todolist is not found")
    void should_return_404_if_todolist_is_not_found() throws Exception {
        // given
        doReturn(null).when(toDoListService).findById(anyLong());

        // when
        mockMvc.perform(get("/api/v1/todolists/123"))
                // then
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should return 201 http status and Location header when creating a todolist")
    void should_return_201_http_status_and_location_header_when_creating_a_todolist() throws Exception {
        // given
        String toDoListToCreateAsJson = "{\"title\": \"ToDoList\", \"description\":\"A ToDoList\"}";
        var toDoList = new ToDoList(123L, null, null);
        doReturn(toDoList).when(toDoListService).create(any());

        // when
        mockMvc.perform(
                        post("/api/v1/todolists")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(toDoListToCreateAsJson)
                )
                // then
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", Matchers.equalTo(("/api/v1/todolists/" + toDoList.getId()))));
    }

    @Test
    @DisplayName("should validate create todolist request")
    void should_validate_create_todolist_request() throws Exception {
        // given
        String toDoListToCreateAsJson = "{\"title\": \"\", \"description\":}";

        // when
        mockMvc.perform(
                        post("/api/v1/todolists")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(toDoListToCreateAsJson)
                )
                // then
                .andExpect(status().isBadRequest());
    }
}
