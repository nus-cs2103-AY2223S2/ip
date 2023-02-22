package response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.DukeException;
import parser.Parser;
import storage.TaskList;

public class CreateResponseTest {
    private TaskList taskList1;
    private TaskList taskList2;
    @BeforeEach
    public void init() {
        this.taskList1 = new TaskList();
        this.taskList2 = new TaskList();
    }
    @Test
    public void testCreateParse() {
        Parser parser = new Parser("todo test1");
        Response actual = parser.parse();
        Response expected = new CreateResponse("test1");
        assertEquals(expected.exec(taskList1), actual.exec(taskList2));
    }
    @Test
    public void testCreateErrorParse() {
        Parser parser = new Parser("todo");
        Response actual = parser.parse();
        DukeException exception = assertThrows(DukeException.class, () -> {
            actual.exec(taskList1);
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "The description of a todo cannot be empty.";
        assertEquals(expectedMessage, actualMessage);
    }
}
