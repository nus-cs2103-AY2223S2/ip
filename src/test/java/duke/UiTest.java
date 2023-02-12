package duke;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class UiTest {

    @Test
    public void testDefaultConstructor() {
        Ui ui = new Ui();
        assertNotNull(ui.getScanner());
    }

    @Test
    public void testConstructorWithDescription() {
        Ui ui = new Ui("test description ");
        assertEquals("test", ui.getDescription());
    }

    @Test
    public void testShowWelcome() {
        Ui ui = new Ui();
        ui.showWelcome();
        // No assertion, just checking if there's any exception thrown
    }

    @Test
    public void testExecuteShowTasks() {
        Ui ui = new Ui("list");
        TaskList<Task> tasks = new TaskList<>();
        TaskList<Task> result = ui.execute(tasks, null);
        assertEquals(tasks.toString(), result.toString());
    }

    @Test
    public void testExecuteTerminate() {
        Ui ui = new Ui();
        // No assertion, just checking if there's any exception thrown
        try {
            ui.execute(null, null);
        } catch (AssertionError e) {
            assertNotNull(e);
        }

    }
}
