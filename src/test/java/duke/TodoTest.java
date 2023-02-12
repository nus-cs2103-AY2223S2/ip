package duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest() {
        ToDo t = new ToDo("go to school");
        assertEquals(t.toString(), "[T][] go to school");
    }
}