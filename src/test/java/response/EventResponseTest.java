package response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.DukeException;
import parser.Parser;
import storage.TaskList;

public class EventResponseTest {
    private TaskList taskList1;
    private TaskList taskList2;
    @BeforeEach
    public void init() {
        this.taskList1 = new TaskList();
        this.taskList2 = new TaskList();
    }
    @Test
    public void testEventParse() {
        Parser parser = new Parser("event test1 /from 2023-01-01 /to 2023-01-02");
        Response actual = parser.parse();
        Response expected = new EventResponse("test1 /from 2023-01-01 /to 2023-01-02");
        assertEquals(expected.exec(taskList1), actual.exec(taskList2));
    }
    @Test
    public void testEventNoDesErrorParse() {
        Parser parser = new Parser("event /from 2023-01-01 /to 2023-01-02");
        Response actual = parser.parse();
        DukeException exception = assertThrows(DukeException.class, () -> {
            actual.exec(taskList1);
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "The description of an event cannot be empty.";
        assertEquals(expectedMessage, actualMessage);
    }
    @Test
    public void testEventNoFromErrorParse() {
        Parser parser = new Parser("event test1 /to 2023-01-02");
        Response actual = parser.parse();
        DukeException exception = assertThrows(DukeException.class, () -> {
            actual.exec(taskList1);
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "The start date cannot be empty."
                + " Date has to be in the format of YYYY-MM-DD (e.g. 2007-12-03)";
        assertEquals(expectedMessage, actualMessage);
    }
    @Test
    public void testEventNoToErrorParse() {
        Parser parser = new Parser("event test1 /from 2023-01-01");
        Response actual = parser.parse();
        DukeException exception = assertThrows(DukeException.class, () -> {
            actual.exec(taskList1);
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "The end date cannot be empty."
                + " Date has to be in the format of YYYY-MM-DD (e.g. 2007-12-03)";
        assertEquals(expectedMessage, actualMessage);
    }
    @Test
    public void testEventWrongDateErrorParse() {
        Parser parser = new Parser("event test1 /from today /to tonight");
        Response actual = parser.parse();
        DukeException exception = assertThrows(DukeException.class, () -> {
            actual.exec(taskList1);
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "Start and end date format should be in the format "
                + "YYYY-MM-DD (e.g. 2007-12-03)";
        assertEquals(expectedMessage, actualMessage);
    }
}
