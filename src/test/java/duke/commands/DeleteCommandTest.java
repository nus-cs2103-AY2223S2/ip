package duke.commands;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.task.TaskList;
import storage.StorageStub;

public class DeleteCommandTest {
    @Test
    public void deleteCommand_deleteFromEmptyTaskList_throwsException() {
        TaskList tasklist = new TaskList();
        StorageStub stub = new StorageStub();

        Command c = new DeleteCommand(0);
        String response = c.execute(tasklist, stub);
        if (response.equals("Task does not exist at specified index.")) {
            return;
        }
        fail("Succeeded deleting from empty list.");
    }
}
