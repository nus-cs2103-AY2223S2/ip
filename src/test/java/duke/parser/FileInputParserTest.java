package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Todo;

class FileInputParserTest {
    @Test
    void parse_validFileInput_success() throws DukeException {
        assertEquals(new Deadline("return book /by 2023-05-17 12:22", "0").toString(),
                FileInputParser.parse("D ~ 0 ~ return book ~ 2023-05-17 12:22").toString());
    }

    @Test
    void parse_invalidFileInput_failure() {
        try {
            assertEquals(new Deadline("return book /by 2023-05-17 12:22", "0").toString(),
                    FileInputParser.parse("D ~return book ~ 2023-05-17 12:22").toString());
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, but Fake Duke doesn't know what that means :-(\n",
                    e.getMessage());
        }
    }

    @Test
    void getTask_validFileInput_success() throws DukeException {
        String[] splitInputs = {"T", "0", "study CS2103"};
        assertEquals(new Todo(splitInputs[2], splitInputs[1]).toString(),
                FileInputParser.getTask(FileInputParser.TaskType.T, splitInputs).toString());
    }

    @Test
    void getTask_invalidFileInput_failure() throws DukeException {
        String[] splitInputs = {"0", "study CS2103"};
        try {
            assertEquals(new Todo(splitInputs[1], splitInputs[0]).toString(),
                    FileInputParser.getTask(FileInputParser.TaskType.T, splitInputs).toString());
            fail();
        } catch (AssertionError e) {
            assertEquals("Local file data/tasks.txt does not have valid format. "
                            + "Todo task is of invalid format in the file.",
                    e.getMessage());
        }
    }
}
