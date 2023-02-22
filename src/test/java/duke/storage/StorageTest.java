package duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;

public class StorageTest {
    private final String testFile = "./data/tasks.ser";
    private Storage storage;
    private TaskList tasks;

    private final String deadline = "01012023 1200";
    private final LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("ddMMyyyy "
            + "HHmm"));

    private final String start = "01012023 1200";
    private final String end = "02012023 1800";
    private final LocalDateTime startDateTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("ddMMyyyy "
            + "HHmm"));
    private final LocalDateTime endDateTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("ddMMyyyy HHmm"));

    @BeforeEach
    void setUp() {
        storage = new Storage(testFile);
        tasks = new TaskList();
    }

    @Test
    void testSave() throws DukeException {
        // Add a task to the list
        tasks.add(new Deadline("Test deadline", deadlineDateTime));
        storage.save(tasks);
        File file = new File(testFile);
        // Assert that the file has been created and has content
        assertNotNull(file);
        assertTrue(file.length() > 0);
    }

    @Test
    void testLoad() throws DukeException {
        // Add a task to the list
        tasks.add(new Event("Test event", startDateTime, endDateTime));
        storage.save(tasks);
        TaskList loadedTasks = storage.load();
        // Assert that the loaded tasks have the same size as the original list
        assertEquals(tasks.size(), loadedTasks.size());
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
