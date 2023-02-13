package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testUnmarkedDeadline() {
        TaskList taskList = new TaskList();
        Deadline.runDeadline(taskList, "produce good vibes /by 2023-10-12");
        assertEquals("[D][ ] produce good vibes (by: Oct 12 2023)", taskList.get(0).toString());
    }

    @Test
    public void testMarkedDeadline() {
        TaskList taskList = new TaskList();
        Deadline.runDeadline(taskList, "do iP /by 2023-02-12");
        TaskList.markTask(taskList, "1");
        assertEquals("[D][X] do iP (by: Feb 12 2023)", taskList.get(0).toString());
    }
}
