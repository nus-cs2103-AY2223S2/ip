package duke;

import duke.model.Task;
import duke.model.ToDo;
import duke.view.cli.TaskView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskViewTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testShowMessage() {
        TaskView view = new TaskView();
        view.showMessage("Hello World!");
        assertEquals("____________________________________________________________\n" +
                "Hello World!\n" +
                "____________________________________________________________\n", outContent.toString());
    }

    @Test
    public void testShowError() {
        TaskView view = new TaskView();
        view.showError("An error has occurred");
        assertEquals("An error has occurred\n", errContent.toString());
    }

    @Test
    public void testRenderTasks() {
        TaskView view = new TaskView();
        List<Task> tasks = Arrays.asList(new ToDo("Task 1"), new ToDo("Task 2"));
        view.renderTasks(tasks);
        assertEquals("____________________________________________________________\n" +
                "Here are the tasks in your list:\n" +
                "1. [T][ ] Task 1\n" +
                "2. [T][ ] Task 2\n" +
                "____________________________________________________________\n", outContent.toString());
    }
}
