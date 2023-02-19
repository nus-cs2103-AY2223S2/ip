package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Parser;

public class ParserTest {

    @Test
    public void splitDeadlineDateCommandTest() {
        String command = "deadline return book /by 02/12/2019 1800";
        String[] splitCommand = Parser.splitCommand(command);
        assertEquals("02-12-2019 1800", splitCommand[2]);
    }

    @Test
    public void splitTaskDescTest() {
        String command = "todo incline walk";
        String[] splitCommand = Parser.splitCommand(command);
        assertEquals("incline walk", splitCommand[1]);
    }
}
