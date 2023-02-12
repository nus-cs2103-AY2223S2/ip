package duke.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testFormatting() {
        ToDo td = new ToDo("play floorball");
        assertEquals("play floorball", td.toString());
    }
}
