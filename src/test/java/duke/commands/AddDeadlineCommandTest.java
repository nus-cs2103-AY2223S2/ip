package duke.commands;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;
import duke.task.TaskList;
import storage.StorageStub;

public class AddDeadlineCommandTest {
    @Test
    public void addDeadline_invalidDate_throwsException() {
        TaskList taskList = new TaskList();
        Storage stub = new StorageStub();


        final String[] invalidDates = {"0", "June 28 2000", "abcd-12-ef"};
        final String description = "test description";
        for (String s: invalidDates) {
            assertExecutingInvalidAddDeadlineThrowsException(description, s, taskList, stub);
        }
    }

    public void assertExecutingInvalidAddDeadlineThrowsException(String description, String date, TaskList taskList,
                                                                 Storage stub) {
        Command c = new AddDeadlineCommand(description, date);
        String response = c.execute(taskList, stub);
        if (response.equals("Date must be in yyyy-mm-dd format.")) {
            return;
        }
        String error = String.format("An addDeadlineCommand was executed successfully with invalid date: %s", date);
        fail(error);
    }
}
