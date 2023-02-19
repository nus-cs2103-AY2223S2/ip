package duke;

import org.junit.jupiter.api.Test;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testMsgOfAdd() {
        Task task = new Task("todo borrow book");
        Duke duke = new Duke();
        assertEquals(duke.messageOfAdd(task), "Got it. I've added this task:\n " + task + "\nNow you have 0 tasks in the list:D");
    }
}
