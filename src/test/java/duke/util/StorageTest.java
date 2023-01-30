package duke.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import duke.task.TaskList;

public class StorageTest {

    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage("./data/test.txt");

    @Test
    public void saveTaskList_emptyList_success() {
        assertDoesNotThrow(() -> storage.save(taskList));
    }
}
