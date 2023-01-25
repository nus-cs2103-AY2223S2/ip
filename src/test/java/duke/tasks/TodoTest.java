package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest() {
        Todo todo = new Todo("grocery");
        String test = "[T][ ] grocery";
        assertEquals(todo.toString(), test);
    }

    @Test
    public void equalsTest() {
        Todo todo = new Todo("grocery");
        Todo test = new Todo("grocery");
        assertEquals(todo, test);
    }

    @Test
    public void toDataFormatStringTest() {
        Todo todo = new Todo("grocery", true);
        String test = "T / 1 / grocery";
        assertEquals(todo.toDataFormatString(), test);
    }
}
