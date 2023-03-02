package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ToDoTest {
    @Test
    public void testStringUnmarked() {
        ToDo task = new ToDo("A123!@# task");
        assertEquals("[T][ ] A123!@# task", task.toString());
        assertEquals("T | 0 | A123!@# task", task.saveString());
    }

    @Test
    public void testStringMarked() {
        ToDo task1 = new ToDo("A123!@# task");
        task1.setDone();
        ToDo task2 = new ToDo("A123!@# task", true);

        assertEquals("[T][X] A123!@# task", task1.toString());
        assertEquals("T | 1 | A123!@# task", task1.saveString());

        assertEquals("[T][X] A123!@# task", task2.toString());
        assertEquals("T | 1 | A123!@# task", task2.saveString());
    }
}
