package duke.commands;

import static org.junit.jupiter.api.Assertions.fail;

import duke.utils.Formatter;
import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.TaskList;
import storage.StorageStub;

public class DeleteCommandTest {
    @Test
    public void deleteCommand_deleteFromEmptyTaskList_throwsException() {
        TaskList tasklist = new TaskList();
        StorageStub stub = new StorageStub();
        Formatter ui = new Formatter();

        try {
            Command c = new DeleteCommand(0);
            c.execute(tasklist, stub);
        } catch (DukeException e) {
            return;
        }
        fail("Deleted from empty list.");
    }
}
