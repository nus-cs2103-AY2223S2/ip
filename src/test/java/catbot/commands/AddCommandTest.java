package catbot.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catbot.CatBotException;
import catbot.storage.Storage;
import catbot.tasklist.TaskList;
import catbot.ui.Ui;

public class AddCommandTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;
    private TaskList tasks = new TaskList(new ArrayList<>());
    private final Ui ui = new Ui();
    private final Storage storage = new Storage("./test/tasklist.txt");

    public AddCommandTest() throws CatBotException {
    }

    // https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    @BeforeAll
    static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    static void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @BeforeEach
    public void reset() throws IOException {
        tasks = new TaskList(new ArrayList<>());
        boolean temp = new File("./test/tasklist.txt").delete();
        boolean temp2 = new File("./test/tasklist.txt").createNewFile();
        outContent.reset();
        errContent.reset();
    }

    @Test
    public void todoTest() throws CatBotException {
        Command testCommand = new AddCommand("This is a test");
        testCommand.execute(tasks, ui, storage);
        assertEquals(1, tasks.size());
        ui.showNext();
        assertEquals(
                "╭─ >^w^< ────────────────────────╮\n"
                + "│ Added new task!                │\n"
                + "│     [T][ ] This is a test      │\n"
                + "│ You have 1 task now.           │\n"
                + "╰────────────────────────────────╯\n\n", outContent.toString());
    }

    @Test
    public void deadlineTest() throws CatBotException {
        Command testCommand = new AddCommand("This is a test", LocalDateTime.parse("2023-01-29T20:00"));
        testCommand.execute(tasks, ui, storage);
        assertEquals(1, tasks.size());
        ui.showNext();
        assertEquals(
                "╭─ >^w^< ────────────────────────────────────────────╮\n"
                + "│ Added new task!                                    │\n"
                + "│     [D][ ] This is a test by 29 Jan 2023, 8:00 PM  │\n"
                + "│ You have 1 task now.                               │\n"
                + "╰────────────────────────────────────────────────────╯\n\n", outContent.toString());
    }

    @Test
    public void eventTest() throws CatBotException {
        Command testCommand = new AddCommand("This is a test",
                LocalDateTime.parse("2023-01-29T20:00"), LocalDateTime.parse("2023-01-29T20:00"));
        testCommand.execute(tasks, ui, storage);
        assertEquals(1, tasks.size());
        ui.showNext();
        assertEquals(
                "╭─ >^w^< ──────────────────────────────────────────────────────────────────────────╮\n"
                + "│ Added new task!                                                                  │\n"
                + "│     [E][ ] This is a test (29 Jan 2023, 8:00 PM – 29 Jan 2023, 8:00 PM)          │\n"
                + "│ You have 1 task now.                                                             │\n"
                + "╰──────────────────────────────────────────────────────────────────────────────────╯\n\n",
                outContent.toString());
    }
}
