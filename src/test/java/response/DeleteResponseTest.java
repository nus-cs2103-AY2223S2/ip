package response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.DukeException;
import parser.Parser;
import storage.TaskList;
import storage.Todo;

public class DeleteResponseTest {
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
    public void testDeleteParse() {
        Parser parser = new Parser("delete 1");
        Response actual = parser.parse();
        Response expected = new DeleteResponse("1");
        assertEquals(expected.exec(taskList1), actual.exec(taskList2));
    }
    @Test
    public void testDeleteErrorParse() {
        Parser parser = new Parser("delete something");
        DukeException exception = assertThrows(DukeException.class, () -> {
            parser.parse();
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "Enter a number after delete!";
        assertEquals(expectedMessage, actualMessage);
    }
}
