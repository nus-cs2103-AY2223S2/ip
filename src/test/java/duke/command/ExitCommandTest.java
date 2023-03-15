package duke.command;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ExitCommandTest {
    @Test
    public void testIsExit(){
        ExitCommand ex = new ExitCommand();
        assertEquals(ex.isExit(), true);
    }
}
