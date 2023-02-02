package duke;

import duke.exceptions.DateTimeFormatException;
import duke.exceptions.DukeException;
import duke.functions.Parser;
import duke.tasks.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
//        } catch (DateTimeFormatException e) {
//                assertEquals("Please give deadline in DD/MM/YY HHMM format.", e.getMessage());
