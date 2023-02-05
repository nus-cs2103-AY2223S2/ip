package duke.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.entities.Task;
import duke.entities.Todo;
import duke.entities.managers.CacheManager;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.stubs.TestStorageStub;
import duke.views.UI;

public class ListCommandTest {
    @Test
    public void listTest_emptyList_emptyTaskStringReturned() {
        try {
            Storage storage = new TestStorageStub("test.txt");
            CacheManager cacheManager = new CacheManager(storage);
            assertEquals("There are no outstanding tasks!", cacheManager.listTasks(task -> true, true));

        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void listTest_addTask_successReturned() {
        try {
            Storage storage = new TestStorageStub("test.txt");
            CacheManager cacheManager = new CacheManager(storage);
            Task task = new Todo("Task 1");
            String success = cacheManager.addTask(task);
            assertEquals("Got it. I've added this task:"
                    + UI.indentMessage(String.valueOf(task))
                    + UI.newLine() + "Now you have " + cacheManager.getTaskList().size() + " tasks in the list." ,
                    success);

        } catch (DukeException e) {
            fail();
        }
    }
}
