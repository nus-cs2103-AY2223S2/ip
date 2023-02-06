package duke.commands;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import storage.StorageStub;


public class AddDeadlineCommandTest {
    @Test
    public void addDeadline_invalidDate_throwsException() {
        TaskList taskList = new TaskList();
        Storage stub = new StorageStub();
        Ui ui = new Ui();


        final String[] invalidDates = {"0", "June 28 2000", "abcd-12-ef"};
        final String description = "test description";
        for (String s: invalidDates) {
            assertExecutingInvalidAddDeadlineThrowsException(description, s, taskList, ui, stub);
        }
    }

    public void assertExecutingInvalidAddDeadlineThrowsException(String description, String date, TaskList taskList,
                                                                    Ui ui, Storage stub) {
        try {
            Command c = new AddDeadlineCommand(description, date);
            c.execute(taskList, ui, stub);
        } catch (DukeException e) {
            return;
        }
        String error = String.format("An addDeadlineCommand was executed successfully with invalid date: %s", date);
        fail(error);
    }
}
