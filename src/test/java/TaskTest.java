import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

public class TaskTest {
    @Test
    public void initTodoTask() {
        Todo todo = new Todo("write essay");
        String expectedOutput = "[T][ ] write essay";
        assertEquals(expectedOutput, todo.toString());
    }

    @Test
    public void initDeadlineTask() {
        Deadline deadline = new Deadline("do that", "2023-03-03");
        String expectedOutput = "[D][ ] do that (by: Mar 3 2023)";
        assertEquals(expectedOutput, deadline.toString());
    }

    @Test
    public void initEventTask() {
        Event event = new Event("event A", "23 March 4pm", "5pm");
        String expectedOutput = "[E][ ] event A (from: 23 March 4pm to: 5pm)";
        assertEquals(expectedOutput, event.toString());
    }
}
