package dudu.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TodoTest {
    private static final String name = "todo task";
    private Todo task;
    public TodoTest() {
        this.task = new Todo(name);
    }

    @Test
    void getStatusTest() {
        assertEquals("0", task.getStatus());
    }
    @Test
    void toStringTest() {
        assertEquals(
                String.format("[T][%s] %s", task.isDone() ? "X" : " ", name),
                task.toString()
        );
    }
}