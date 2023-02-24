package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import duke.ToDo;

public class ToDoTest {
  @Test
  public void markTest() {
    ToDo toDo = new ToDo("markTest for ToDo");
    assertEquals(toDo.mark().toString(), "[T][X] markTest for ToDo");
  }

  @Test
  public void getMarkTest() {
    ToDo toDo = new ToDo("getMarkTest for ToDo");
    assertEquals(toDo.getType(), "T");
  }

}