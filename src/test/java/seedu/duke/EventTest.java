package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventTest() throws DukeException {
        Parser parser = new Parser();
        TaskList tasks = new TaskList();
        parser.parse("event eat /from 2018-01-01 /to 2018-02-02", tasks);
        assertEquals(tasks.list.get(0).toString(), "[ E ][ X ] eat from: 2018-01-01 to: 2018-02-02");
    }
}
