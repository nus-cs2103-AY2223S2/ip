package duke.storage;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

public class StorageTest {
    @Test
    public void testSaveAndLoadTask() {
        try {
            List<Task> tasks = new ArrayList<>();
            tasks.add(new Todo("Write unit tests", false));
            tasks.add(new Deadline("complete unit tests", false, "26/1/2023 2303"));
            tasks.add(new Event("unit test celebrations", false, "26/1/2023 2303", "26/1/2023 2359"));
            Storage.save(tasks);
            assertArrayEquals(Storage.load().toArray(), tasks.toArray());
        } catch (Exception e) {
            fail();
        }
        
    }
}
