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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandTest {
    private final String testFile = "./data/tasks.ser";
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        storage = new Storage(testFile);
        ui = new Ui();
    }

    @Test
    public void testAddCommand() throws DukeException {
        File file = new File(testFile);
        Task task = new Deadline("Test deadline", "01012023 1200");
        Command command = new Command.AddCommand(task);
        command.execute(tasks, ui, storage);
        assertEquals(1, tasks.getSize());
        assertEquals(task, tasks.getTask(0));
        assertEquals(true, file.length() > 0);
        // Difficult to test for ui
    }

    @Test
    public void testMarkCommand() throws DukeException {
        Task task = new Deadline("Test deadline", "01012023 1200");
        assertEquals(false, task.isDone());
        tasks.addTask(task);
        Command command = new Command.MarkCommand(0);
        command.execute(tasks, ui, storage);
        assertEquals(true, storage.load().getTask(0).isDone());
        // Difficult to test for ui
    }

    @Test
    public void testUnmarkCommand() throws DukeException {
        Task task = new Deadline("Test deadline", "01012023 1200");
        task.setDone(true);
        assertEquals(true, task.isDone());
        tasks.addTask(task);
        Command command = new Command.UnmarkCommand(0);
        command.execute(tasks, ui, storage);
        assertEquals(false, storage.load().getTask(0).isDone());
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
