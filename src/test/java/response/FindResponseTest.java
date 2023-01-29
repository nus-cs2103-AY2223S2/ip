package response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.Parser;
import storage.TaskList;
import storage.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindResponseTest {
    private TaskList taskList;
    @BeforeEach
    public void init() {
        this.taskList = new TaskList();
        taskList.createToDo(new Todo("test1"));
    }
    @Test
    public void testFindTrue() {
        Parser parser = new Parser("find test1");
        Response actual = parser.parse();
        Response expected = new FindResponse("test1");
        assertEquals(expected.exec(taskList), actual.exec(taskList));
    }
    @Test
    public void testFindFalse() {
        Parser parser = new Parser("find something");
        Response actual = parser.parse();
        Response expected = new FindResponse("something");
        assertEquals(expected.exec(taskList), actual.exec(taskList));
    }
}
