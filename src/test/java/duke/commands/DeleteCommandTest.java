package duke.commands;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import storage.StorageStub;

public class DeleteCommandTest {
    @Test
    public void deleteCommand_deleteFromEmptyTaskList_throwsException() {
        TaskList tasklist = new TaskList();
        StorageStub stub = new StorageStub();
        Ui ui = new Ui();

        try {
            Command c = new DeleteCommand(0);
            c.execute(tasklist, ui, stub);
        } catch (DukeException e) {
            return;
        }
        fail("Deleted from empty list.");
    }
}
