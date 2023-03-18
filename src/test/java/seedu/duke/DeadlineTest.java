package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void deadlineTest() throws DukeException {
        Parser parser = new Parser();
        TaskList tasks = new TaskList();
        parser.parse("deadline eat /by 2018-01-01", tasks);
        assertEquals(tasks.list.get(0).toString(), "[ D ][ X ] eat by: 2018-01-01 ");

    }
}
