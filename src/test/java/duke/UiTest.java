package duke;

import duke.task.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UiTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testShowDeleteTask(){
        Todo testTask = new Todo("test");
        Ui testUi = new Ui();
        testUi.showDeleteTask(testTask, new TaskList());
        assertEquals("Deleted: [T][ ] test\r\n" +
                "Now you have 0 task(s) in the list.", outputStreamCaptor.toString().trim());
    }
}
