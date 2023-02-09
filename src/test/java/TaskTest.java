import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
  @Test
  public void todoString() {
    Todo todo = new Todo("party");
    todo.mark();
    assertEquals("[T][X] party", todo.taskString());
  }

  @Test
  public void eventString() {
    Event event = new Event("party","2023-02-09 0000",
        "2023-02-09 0400");
    assertEquals("[E][ ] party (from: 2023-02-09T00:00 to: 2023-02-09T04:00)",
        event.taskString());
  }

  @Test
  public void deadLine() {
    Deadline deadline = new Deadline("return book", "2023-02-09 0000");
    assertEquals("[D][ ] return book (by: 2023-02-09T00:00)",
        deadline.taskString());
  }
}
