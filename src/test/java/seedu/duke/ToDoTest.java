package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toDoTest() throws DukeException {
        Parser parser = new Parser();
        TaskList tasks = new TaskList();
        parser.parse("todo eat", tasks);
        assertEquals(tasks.list.get(0).toString(), "[ T ][ X ] eat");

    }
}
