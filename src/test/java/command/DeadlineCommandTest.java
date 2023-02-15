package command;

import org.junit.jupiter.api.Test;
import task.TaskManager;
import util.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineCommandTest {

    //creates new task array
    TaskManager tm = new TaskManager();
    @Test
    public void executeCommand_checkAddToList_success() throws DukeException {
        DeadlineCommand dc = new DeadlineCommand(tm, "test program /by 25/12/23 1150");
        dc.executeCommand();
        assertEquals(1, tm.getTaskArraySize());

    }

    @Test
    public void executeCommand_checkTaskAddedToList_success() throws DukeException {
        DeadlineCommand dc = new DeadlineCommand(tm, "test program /by 25/12/23 1150");
        dc.executeCommand();
        assertEquals("[D][ ] test program (by: 25 Dec 2023 11:50 AM)", tm.printTask(0));
    }

    @Test
    public void executeCommand_invalidDateTime_exceptionThrown() {
        try {
            DeadlineCommand dc = new DeadlineCommand(tm, "test /by 25/12/23 11:50PM");
            dc.executeCommand();
            assertEquals("[D][ ] test program (by: 25/12/23 11:50 AM)", tm.printTask(0));
            fail();
        } catch (Exception e) {
            assertEquals("Please enter date in dd/mm/yy and time in hhmm 24hr format!", e.getMessage());
        }
    }

    @Test
    public void executeCommand_invalidUserInput_exceptionThrown() {
        try {
            DeadlineCommand dc = new DeadlineCommand(tm, "test /by");
            dc.executeCommand();
            assertEquals("[D][ ] test program (by: 25/12/23 11:50 AM)", tm.printTask(0));
            fail();
        } catch (Exception e) {
            assertEquals("Please add a description, date and time e.g. homework /by 12/12/12 2359", e.getMessage());
        }
    }
}
