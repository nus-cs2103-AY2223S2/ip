package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.functions.Parser;
import duke.tasks.TaskList;

public class DeadlineTest {
    @Test
    public void parseDeadline_wrongFormat_exceptionThrown() {
        try {
            String input = "deadline return book /25/12-22";
            TaskList dl = new TaskList();
            Parser p = new Parser(dl);
            p.handleInput(input);
        } catch (DukeException e) {
            assertEquals("Please input the datetime format in /by DD/MM/YY HHMM.", e.getMessage());
        }
    }
}
