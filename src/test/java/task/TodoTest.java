package task;

import org.junit.jupiter.api.Test;
import duke.tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void checkCorrectToString() {
        Todo testInput = new Todo("todo do the dishes");
        assertEquals(testInput.toString(), ". [T][ ] do the dishes");
    }

    @Test
    public void checkCorrectMarkStatus() {
        Todo testInput = new Todo("todo do the dishes");
        testInput.setMark();
        assertEquals(testInput.toString(), ". [T][X] do the dishes");
        testInput.setMark();
        assertEquals(testInput.toString(), ". [T][ ] do the dishes");
    }
}
