package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    //test setup
    ToDo td = new ToDo(1, "run");

    @Test
    public void toFile_format_returnFormatted() {
        String expected = "TD|false|run";
        assertEquals(td.toFile(), expected);
    }

    @Test
    public void toString_format_returnFormatted() {
        String expected = "1. [T][ ] run";
        assertEquals(expected, td.toString());
    }
}
