package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void dummyTest() throws DukeException {
        Parser parser = new Parser();
        TaskList tasks = new TaskList();
        parser.parse("todo eat", "todo eat".split(" "), 0, tasks);
        parser.parse("deadline eat /by 2018-01-01", "deadline eat /by 2018-01-01".split(" "), 1, tasks);
        parser.parse("event eat /from 2018-01-01 /to 2018-02-02", "event eat /from 2018-01-01 /to 2018-02-02".split(" "), 2, tasks);
        assertEquals(tasks.list.get(0).toString(), "[ T ][ N ] eat ");
        assertEquals(tasks.list.get(1).toString(), "[ D ][ N ] eat by: 2018-01-01 ");
        assertEquals(tasks.list.get(2).toString(), "[ E ][ N ] eat from: 2018-01-01 to: 2018-02-02");
    }
}
