package duke.task;

import duke.task.Deadline;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        Task t = new Event("project meeting ", "2019-06-15", "2019-06-19");
        assertEquals("[E][ ] project meeting (from: Jun 15 2019 to: Jun 19 2019)", t.toString());
    }

    @Test
    public void toStringTest2() {
        Task t = new Event("return book ", "2020-12-20", "2021-10-19");
        assertEquals("[E][ ] return book (from: Dec 20 2020 to: Oct 19 2021)", t.toString());
    }
}
