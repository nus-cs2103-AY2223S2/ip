package duke.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.entities.Task;
import duke.entities.TaskList;
import duke.entities.Todo;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.stubs.TestStorageStub;
import duke.views.UI;

public class ListCommandTest {
    @Test
    public void listTest_emptyList_emptyTaskStringReturned() {
        try {
            Storage storage = new TestStorageStub("test.txt");
            TaskList taskList = new TaskList(storage);
            assertEquals("There are no outstanding tasks!", taskList.listTasks(task -> true, true));

        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void listTest_addTask_successReturned() {
        try {
            Storage storage = new TestStorageStub("test.txt");
            TaskList taskList = new TaskList(storage);
            Task task = new Todo("Task 1");
            String success = taskList.addTask(task);
            assertEquals("Got it. I've added this task:"
                    + UI.indentMessage(String.valueOf(task))
                    + UI.newLine() + "Now you have " + taskList.getTaskList().size() + " tasks in the list." ,
                    success);

        } catch (DukeException e) {
            fail();
        }
    }
}
