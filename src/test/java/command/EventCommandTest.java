package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import task.TaskManager;
import util.DukeException;

public class EventCommandTest {
    private TaskManager taskManager = new TaskManager();
    @Test
    public void executeCommand_checkAddToList_success() throws DukeException {
        EventCommand ec = new EventCommand("party /from 12/2/23 0600 /to 12/2/23 1000");
        ec.executeCommand(taskManager);
        assertEquals(1, taskManager.getTaskArraySize());

    }

    @Test
    public void executeCommand_checkTaskAddedToList_success() throws DukeException {
        EventCommand ec = new EventCommand("party /from 12/2/23 0600 /to 12/2/23 1000");
        ec.executeCommand(taskManager);
        assertEquals("[E][ ] party (Start: 12 Feb 2023 06:00 AM | End: 12 Feb 2023 10:00 AM)",
                taskManager.printTask(0));
    }

    @Test
    public void executeCommand_invalidDateTime_exceptionThrown() {
        try {
            EventCommand ec = new EventCommand("party /from 12/2/23 0600 /to 12/2/2 1000");
            ec.executeCommand(taskManager);
            assertEquals("[E][ ] party (Start: 12 Feb 2023 06:00 AM | End: 12 Feb 2023 10:00 AM)",
                    taskManager.printTask(0));
            fail();
        } catch (Exception e) {
            assertEquals("Please enter date in dd/mm/yy and time in hhmm 24hr format!", e.getMessage());
        }
    }

    @Test
    public void executeCommand_invalidUserInputV1_exceptionThrown() {
        try {
            EventCommand ec = new EventCommand("party /from 12/2/23 0600");
            ec.executeCommand(taskManager);
            fail();
        } catch (Exception e) {
            assertEquals("Please add a description, date and time e.g. party /from 12/2/23 1800 /to 12/2/23 2200",
                    e.getMessage());
        }
    }

    @Test
    public void executeCommand_invalidUserInputV2_exceptionThrown() {
        try {
            EventCommand ec = new EventCommand("party /to 12/2/2");
            ec.executeCommand(taskManager);
            fail();
        } catch (Exception e) {
            assertEquals("Please add a description, date and time e.g. party /from 12/2/23 1800 /to 12/2/23 2200",
                    e.getMessage());
        }
    }

    @Test
    public void executeCommand_invalidUserInputV3_exceptionThrown() {
        try {
            EventCommand ec = new EventCommand("party");
            ec.executeCommand(taskManager);
            fail();
        } catch (Exception e) {
            assertEquals("Please add a description, date and time e.g. party /from 12/2/23 1800 /to 12/2/23 2200",
                    e.getMessage());
        }
    }
}
