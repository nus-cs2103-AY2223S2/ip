package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StorageTest {
    private final String testFile = "./data/tasks.ser";
    private Storage storage;
    private TaskList tasks;

    @BeforeEach
    void setUp() {
        storage = new Storage(testFile);
        tasks = new TaskList();
    }

    @Test
    void testSave() throws DukeException {
        // Add a task to the list
        tasks.addTask(new Deadline("Test deadline", "01012023"));
        storage.save(tasks);
        File file = new File(testFile);
        // Assert that the file has been created and has content
        assertNotNull(file);
        assertEquals(true, file.length() > 0);
    }

    @Test
    void testLoad() throws DukeException {
        // Add a task to the list
        tasks.addTask(new Event("Test event", "01012023 1200", "02012023 1800"));
        storage.save(tasks);
        TaskList loadedTasks = storage.load();
        // Assert that the loaded tasks have the same size as the original list
        assertEquals(tasks.getSize(), loadedTasks.getSize());
    }

    @AfterEach
    public void tearDown() {
        // Clean up the test file after each test
        try {
            Files.deleteIfExists(Paths.get(testFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
