package duke;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    private final String testFile = "./data/tasks.ser";
    private Duke duke;
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage(testFile);
        duke = new Duke(testFile);
    }

    @Test
    public void testRun() {
        // Setup input and output streams for the test
        String input = "list\nbye\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        // Run the Duke.run() method
        Duke duke = new Duke("./data/tasks.ser");
        duke.run();

        // Check the output
        String expectedOutput = "  /\\_/\\\n" +
                " /◞   ◟\\\n" +
                "( ◕   ◕ )\n" +
                " \\     /\n" +
                "  \\   /\n" +
                "   \\ /\n" +
                "    ●\n" +
                "BorzAI\n" +
                "\n" +
                "\tWhen all I do is for you, Kermie ♥\n" +
                "\tWhat can I do for you?\n" +
                "\n" +
                "\t__________________________________________________________\n" +
                "\tHere are the tasks in your list:\n" +
                "\t__________________________________________________________\n" +
                "\t__________________________________________________________\n" +
                "\tWoof (╯ᆺ╰๑)\n" +
                "\t__________________________________________________________\n";
        assertEquals(expectedOutput, out.toString());

        // Reset input and output streams
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void testMain() {
        // Setup input and output streams for the test
        String input = "list\nbye\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        // Run the Duke.main() method
        Duke.main(new String[]{});

        // Check the output
        String expectedOutput = "  /\\_/\\\n" +
                " /◞   ◟\\\n" +
                "( ◕   ◕ )\n" +
                " \\     /\n" +
                "  \\   /\n" +
                "   \\ /\n" +
                "    ●\n" +
                "BorzAI\n" +
                "\n" +
                "\tWhen all I do is for you, Kermie ♥\n" +
                "\tWhat can I do for you?\n" +
                "\n" +
                "\t__________________________________________________________\n" +
                "\tHere are the tasks in your list:\n" +
                "\t__________________________________________________________\n" +
                "\t__________________________________________________________\n" +
                "\tWoof (╯ᆺ╰๑)\n" +
                "\t__________________________________________________________\n";
        assertEquals(expectedOutput, out.toString());

        // Reset input and output streams
        System.setIn(System.in);
        System.setOut(System.out);
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
