package response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.DukeException;
import parser.Parser;
import storage.TaskList;

public class DeadlineResponseTest {
    private TaskList taskList1;
    private TaskList taskList2;
    @BeforeEach
    public void init() {
        this.taskList1 = new TaskList();
        this.taskList2 = new TaskList();
    }
    @Test
    public void testDeadlineParse() {
        Parser parser = new Parser("deadline test1 /by 2023-01-01");
        Response actual = parser.parse();
        Response expected = new DeadlineResponse("test1 /by 2023-01-01");
        assertEquals(expected.exec(taskList1), actual.exec(taskList2));
    }
    @Test
    public void testDeadlineNoDesErrorParse() {
        Parser parser = new Parser("deadline /by 2023-01-01");
        Response actual = parser.parse();
        DukeException exception = assertThrows(DukeException.class, () -> {
            actual.exec(taskList1);
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "The description of a deadline cannot be empty.";
        assertEquals(expectedMessage, actualMessage);
    }
    @Test
    public void testDeadlineNoByErrorParse() {
        Parser parser = new Parser("deadline test1");
        Response actual = parser.parse();
        DukeException exception = assertThrows(DukeException.class, () -> {
            actual.exec(taskList1);
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "The deadline cannot be empty."
                + " Deadline has to be in the format of YYYY-MM-DD (e.g. 2007-12-03)";
        assertEquals(expectedMessage, actualMessage);
    }
    @Test
    public void testDeadlineWrongByErrorParse() {
        Parser parser = new Parser("deadline test1 /by tonight");
        Response actual = parser.parse();
        DukeException exception = assertThrows(DukeException.class, () -> {
            actual.exec(taskList1);
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "Deadline date format should be in the format "
                + "YYYY-MM-DD (e.g. 2007-12-03)";
        assertEquals(expectedMessage, actualMessage);
    }
}
