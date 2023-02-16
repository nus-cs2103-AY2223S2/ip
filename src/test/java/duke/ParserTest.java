package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Parser;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class ParserTest {

    @Test
    public void splitDeadlineDateCommandTest() {
        String command = "deadline return book /by 02/12/2019 1800";
        String[] splitCommand = Parser.splitCommand(command);
        assertEquals("by 02-12-2019 1800", splitCommand[2]);
    }

    @Test
    public void splitTaskDescTest() {
        String command = "todo incline walk";
        String[] splitCommand = Parser.splitCommand(command);
        assertEquals("incline walk", splitCommand[1]);
    }

    @Test
    public void parseUnknownCommandTest() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("return book"));
        TaskList tasklist = new TaskList(tasks);
        assertEquals(null, Parser.parse("sdf"));
    }
}
