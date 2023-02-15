package duke;

import duke.Tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {
    @Test
    public void markTest() {
        Todo t = new Todo("Sleep", false);
        assertEquals(t.mark().toString(), "[T][X] Sleep");
    }

    @Test
    public void reformatTest() {
        Todo t = new Todo("Revise CS2103", false);
        assertEquals(t.reformat(), "T | 0 | Revise CS2103");
    }

}
