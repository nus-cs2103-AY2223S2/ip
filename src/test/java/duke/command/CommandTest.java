package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class CommandTest {
    static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    static final Storage s = Storage.create();
    static final Ui ui = new Ui();
    static final TaskList ts = TaskList.create(s, ui);

    @BeforeAll
    public static void setup() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public static void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
