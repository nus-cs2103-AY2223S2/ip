import Duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
  @Test
  public void todoString(){
    Todo todo = new Todo("party");
    todo.mark();
    assertEquals("[T][X] party", todo.taskString());
  }
}
