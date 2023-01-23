package duke.util;

import duke.task.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import duke.DukeException;

public class StorageTest {

    TaskList taskList = new TaskList();
    Storage storage = new Storage("./data/test.txt");

    @Test
    public void saveTaskList_emptyList_success() {
        assertDoesNotThrow(() -> storage.save(taskList));
    }
}
