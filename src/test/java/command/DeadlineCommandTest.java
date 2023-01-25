package command;

import org.junit.jupiter.api.Test;
import task.TaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineCommandTest {

    //creates new task array
    TaskManager tm = new TaskManager();
    @Test
    public void executeCommand_checkAddToList_success(){
        DeadlineCommand dc = new DeadlineCommand(tm, "test program /by 25/12/23 11:50PM");
        dc.executeCommand();
        assertEquals(1, tm.getTaskArraySize());
    }

    @Test
    public void executeCommand_checkTaskAddedToList_success(){
        DeadlineCommand dc = new DeadlineCommand(tm, "test program /by 25/12/23 11:50PM");
        dc.executeCommand();
        assertEquals("[D][ ] test program (by: 25/12/23 11:50PM)", tm.printTask(0));
    }

    @Test
    public void executeCommand_invalidUserInput_exceptionThrown(){
        try {
            DeadlineCommand dc = new DeadlineCommand(tm, "test /by 25/12/23 11:50PM");
            dc.executeCommand();
            assertEquals("[D][ ] test program (by: 25/12/23 11:50PM)", tm.printTask(0));
            fail();
        } catch (Exception e) {
            //assertEquals("no");
        }

    }
}
