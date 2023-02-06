package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {
    private final String testFile = "./data/tasks.ser";
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    String deadline = "01012023 1200";
    LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("ddMMyyyy HHmm"));

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        storage = new Storage(testFile);
        ui = new Ui();
    }

    @Test
    public void testAddCommand() throws DukeException {
        File file = new File(testFile);
        Task task = new Deadline("Test deadline", deadlineDateTime);
        Command command = new Command.AddCommand(task);
        command.execute(tasks, ui, storage);
        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
        assertTrue(file.length() > 0);
        // Difficult to test for ui
    }

    @Test
    public void testMarkCommand() throws DukeException {
        Task task = new Deadline("Test deadline", deadlineDateTime);
        assertFalse(task.isDone());
        tasks.add(task);
        Command command = new Command.MarkCommand(0);
        command.execute(tasks, ui, storage);
        assertTrue(storage.load().get(0).isDone());
        // Difficult to test for ui
    }

    @Test
    public void testUnmarkCommand() throws DukeException {
        Task task = new Deadline("Test deadline", deadlineDateTime);
        task.setDone(true);
        assertTrue(task.isDone());
        tasks.add(task);
        Command command = new Command.UnmarkCommand(0);
        command.execute(tasks, ui, storage);
        assertFalse(storage.load().get(0).isDone());
        // Difficult to test for ui
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
