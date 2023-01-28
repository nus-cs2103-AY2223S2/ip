package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void addToDoTest() {
        Todo task = new Todo("Bench press 100kg");
        assertEquals("[T][ ] Bench press 100kg", task.toString());
    }

    @Test
    public void markTest() {
        Todo task = new Todo("Bench press 100kg");
        task.mark();
        assertEquals("[T][X] Bench press 100kg", task.toString());
    }

    @Test
    public void unmarkTest() {
        Todo task = new Todo("Bench press 100kg");
        task.mark();
        task.unmark();
        assertEquals("[T][ ] Bench press 100kg", task.toString());
    }
}

