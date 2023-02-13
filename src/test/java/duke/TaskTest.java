package duke;

import duke.task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void getStatusTest() {
        Task ts = new Task(true, "test");
        assertEquals(false, ts.getMark());
    }


}
