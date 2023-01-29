package duke.Tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    //test setup
    ToDo td = new ToDo(1, "run");

    @Test
    public void testToFile() {
        String expected = "TD|false|run";
        assertEquals(td.toFile(), expected);
    }

    @Test
    public void testToString() {
        String expected = "1. [T][ ] run";
        assertEquals(expected, td.toString());
    }
}
