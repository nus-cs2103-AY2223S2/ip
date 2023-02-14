package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    //test setup
    private ToDo td = new ToDo("run");

    @Test
    public void toFile_format_returnFormatted() {
        String expected = "TD|false|run";
        assertEquals(td.toFile(), expected);
    }

    @Test
    public void toString_format_returnFormatted() {
        String expected = "[T][ ] run";
        assertEquals(expected, td.toString());
    }
}
