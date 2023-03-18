package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteTest {
    @Test
    public void deleteTest() throws DukeException {
        Parser parser = new Parser();
        TaskList tasks = new TaskList();
        parser.parse("todo eat", tasks);
        parser.parse("deadline eat /by 2018-01-01", tasks);
        parser.parse("event eat /from 2018-01-01 /to 2018-02-02", tasks);

        assertEquals(tasks.list.size(), 3);
        parser.parse("delete 3", tasks);
        parser.parse("delete 2", tasks);
        assertEquals(tasks.list.size(), 1);
    }
}
