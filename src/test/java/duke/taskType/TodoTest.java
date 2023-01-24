package duke.taskType;

import duke.taskType.*;
import duke.*;
import duke.commands.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {
    @Test
    public void Todotest() {
        Todo t = new Todo("lash");
        assertEquals("[T][ ]lash", t.toString());
    }
}
