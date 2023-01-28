package response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.DukeException;
import parser.Parser;
import storage.TaskList;
import storage.Todo;

public class UnMarkResponseTest {
    private TaskList taskList1;
    private TaskList taskList2;
    @BeforeEach
    public void init() {
        this.taskList1 = new TaskList();
        this.taskList1.createToDo(new Todo("test1"));
        this.taskList2 = new TaskList();
        this.taskList2.createToDo(new Todo("test1"));
    }
    @Test
    public void testUnMarkParse() {
        Parser parser = new Parser("unmark 1");
        Response actual = parser.parse();
        Response expected = new UnMarkResponse("1");
        assertEquals(expected.exec(taskList1), actual.exec(taskList2));
    }
    @Test
    public void testUnMarkErrorParse() {
        Parser parser = new Parser("unmark something");
        DukeException exception = assertThrows(DukeException.class, () -> {
            parser.parse();
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "Enter a number after unmark!";
        assertEquals(expectedMessage, actualMessage);
    }
}
