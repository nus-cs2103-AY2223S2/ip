package duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void toStringTest(){
        Todo t = new Todo("run");
        assertEquals(t.toString(), "T | 0 | run");
    }
}
