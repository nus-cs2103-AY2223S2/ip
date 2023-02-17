package response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import parser.Parser;
import storage.Deadline;
import storage.Event;
import storage.TaskList;
import storage.Todo;

public class ListResponseTest {
    private TaskList taskList;
    @BeforeEach
    public void init() {
        this.taskList = new TaskList();
        this.taskList.createToDo(new Todo("test1"));
        this.taskList.createToDo(new Deadline("test2", LocalDate.parse("2023-01-01")));
        this.taskList.createToDo(new Event("test3", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-02")));
    }
    @Test
    public void testListParse() {
        Parser parser = new Parser("list");
        Response actual = parser.parse();
        Response expected = new ListResponse();
        assertEquals(expected.exec(taskList), actual.exec(taskList));
    }
}
