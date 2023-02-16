package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import task.TaskManager;
import util.DukeException;

public class DeadlineCommandTest {
    private TaskManager taskManager = new TaskManager();
    @Test
    public void executeCommand_checkAddToList_success() throws DukeException {
        DeadlineCommand dc = new DeadlineCommand("test program /by 25/12/23 1150");
        dc.executeCommand(taskManager);
        assertEquals(1, taskManager.getTaskArraySize());

    }

    @Test
    public void executeCommand_checkTaskAddedToList_success() throws DukeException {
        DeadlineCommand dc = new DeadlineCommand("test program /by 25/12/23 1150");
        dc.executeCommand(taskManager);
        assertEquals("[D][ ] test program (by: 25 Dec 2023 11:50 AM)", taskManager.printTask(0));
    }

    @Test
    public void executeCommand_invalidDateTime_exceptionThrown() {
        try {
            DeadlineCommand dc = new DeadlineCommand("test /by 25/12/23 11:50PM");
            dc.executeCommand(taskManager);
            assertEquals("[D][ ] test program (by: 25/12/23 11:50 AM)", taskManager.printTask(0));
            fail();
        } catch (Exception e) {
            assertEquals("Please enter date in dd/mm/yy and time in hhmm 24hr format!", e.getMessage());
        }
    }

    @Test
    public void executeCommand_invalidUserInput_exceptionThrown() {
        try {
            DeadlineCommand dc = new DeadlineCommand("test /by");
            dc.executeCommand(taskManager);
            assertEquals("[D][ ] test program (by: 25/12/23 11:50 AM)", taskManager.printTask(0));
            fail();
        } catch (Exception e) {
            assertEquals("Please add a description, date and time e.g. homework /by 12/12/12 2359", e.getMessage());
        }
    }
}
