package duke.commands;

import duke.exception.DukeException;
import duke.commands.DeleteCommand;

import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;
import storage.StorageStub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class DeleteCommandTest {
    @Test
    public void deleteCommand_deleteFromEmptyTaskList_throwsException() {
        int index = 0;
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
