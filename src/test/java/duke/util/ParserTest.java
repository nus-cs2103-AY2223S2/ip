package duke.util;

import duke.task.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import duke.DukeException;

public class ParserTest {

    TaskList taskList = new TaskList();
    Parser parser = new Parser(taskList, null, null);

    @Test
    public void todoCommand_noInput_exceptionThrown() {
        assertThrows(DukeException.class, () -> parser.parseUserCommand("todo"));
    }
}
