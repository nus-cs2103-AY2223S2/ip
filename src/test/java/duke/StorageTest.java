package duke;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void storageLoadTest() throws DukeException {
        Storage storageTest = new Storage("src/test/java");
        ArrayList<Task> testTasks = storageTest.load();
        Task testTask1 = testTasks.get(0);
        Task testTask2 = testTasks.get(1);
        assertEquals("[T][X] test1", testTask1.toString());
        assertEquals("[T][ ] test2", testTask2.toString());
    }


}
