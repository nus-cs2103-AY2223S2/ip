package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.Main.Flag;
import duke.task.DummyTask;

/**
 * Contains tests for Duke class.
 * WARNING: UI.getRecentMessages() is assumed to work, in order for the tests to work.
 *          Ensure that UI.getRecentMessages() also pass their own tests first!
 */
public class DukeTest {

    private Duke d;

    @BeforeAll
    static void oneTimeSetUp() {
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
    }

    @BeforeEach
    void setUp() {
        // Prevent Duke from loading any saved files
        ArrayList<Flag> f = new ArrayList<>();
        f.add(Flag.NO_LOAD_SAVES);
        Duke.setFlags(f);
        d = new Duke();
        d.storage.setSaveFilePath(TestConfigs.TEST_SAVE_FILE_PATH);
    }

    @Test
    void addNewTask_oneTask() {
        assertEquals(0, d.taskList.size());
        d.addNewTask(new DummyTask());
        assertEquals(1, d.taskList.size());
    }

    @Test
    void addNewTask_manyTasks() {
        assertEquals(0, d.taskList.size());
        for (int i = 0; i < 1000; i++) {
            d.addNewTask(new DummyTask());
        }
        assertEquals(1000, d.taskList.size());
    }

    @Test
    void displayTaskCount_noTask() {
        d.displayTaskCount();
        assertEquals("You do not have any task!\n", d.ui.getRecentMessages());
    }

    @Test
    void displayTaskCount_aFewTasks() {
        d.addNewTask(new DummyTask());
        d.addNewTask(new DummyTask());
        d.addNewTask(new DummyTask());
        d.ui.getRecentMessages(); // Output unwanted, just need to clear the internal cache.

        d.displayTaskCount();
        assertEquals("Now you have 3 task(s) in the list.\n", d.ui.getRecentMessages());
    }

    @Test
    void displayTaskCount_manyTasks() {
        for (int i = 0; i < 1000; i++) {
            d.addNewTask(new DummyTask());
        }
        d.ui.getRecentMessages(); // Output unwanted, just need to clear the internal cache.

        d.displayTaskCount();
        assertEquals("Now you have 1000 task(s) in the list.\n", d.ui.getRecentMessages());
    }

    @AfterAll
    static void oneTimeTearDown() {
        System.setOut(System.out);
    }
}
