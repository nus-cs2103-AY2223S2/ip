package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testUnmarkedEvent() {
        TaskList taskList = new TaskList();
        Event.runEvent(taskList, "sleep enough /at 2023-10-12");
        assertEquals("[E][ ] sleep enough (at: Oct 12 2023)", taskList.get(0).toString());
    }

    @Test
    public void testMarkedEvent() {
        TaskList taskList = new TaskList();
        Event.runEvent(taskList, "pet some cats /at 2023-02-12");
        TaskList.markTask(taskList, "1");
        assertEquals("[E][X] pet some cats (at: Feb 12 2023)", taskList.get(0).toString());
    }
}
