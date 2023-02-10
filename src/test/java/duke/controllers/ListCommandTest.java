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

public class ListCommandTest {
    private final Storage storage = new TestStorageStub("test.txt");
    @Test
    public void listTest_emptyList_emptyTaskStringReturned() {
        try {
            CacheManager cacheManager = new CacheManager(storage);
            assertEquals("There are no outstanding tasks!", cacheManager.listTasks(task -> true, true));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void listTest_addMultipleTasks_successReturned() {
        try {
            CacheManager cacheManager = new CacheManager(storage);
            for (int i = 0; i < 10; i++) {
                Task task = new Todo("Task " + (i + 1));
                cacheManager.addTask(task);
            }
            assertEquals(10, cacheManager.getTaskList().size());
        } catch (DukeException e) {
            fail();
        }
    }
}
