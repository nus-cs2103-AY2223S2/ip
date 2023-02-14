package duke.util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.task.TaskList;

public class ParserTest {

    private final TaskList taskList = new TaskList();
    private final Parser parser = new Parser(taskList, null);

    @Test
    public void todoCommand_noInput_exceptionThrown() {
        assertThrows(DukeException.class, () -> parser.parseUserCommand("todo"));
    }
}
