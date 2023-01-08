package htw.berlin.webtech.demo.Service;

import htw.berlin.webtech.demo.persistence.TaskEntity;
import htw.berlin.webtech.demo.persistence.ToDoListEntity;
import htw.berlin.webtech.demo.service.TaskTransformer;
import htw.berlin.webtech.demo.service.ToDoListTransformer;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.doReturn;

public class ToDoListTransformerTest implements WithAssertions {

    private final ToDoListTransformer underTest = new ToDoListTransformer();

    @Test
    @DisplayName("should transform TaskEntity to Task")
    void should_transform_todolist_entity_to_todolist() {
        // given
        var todolistEntity = Mockito.mock(ToDoListEntity.class);
        doReturn(1L).when(todolistEntity).getId();
        doReturn("A Test Title").when(todolistEntity).getTitle();
        doReturn("A Test Description").when(todolistEntity).getDescription();

        // when
        var result = underTest.transformEntity(todolistEntity);

        // then
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getTitle()).isEqualTo("A Test Title");
        assertThat(result.getDescription()).isEqualTo("A Test Description");
    }

}

