package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

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
        try {
            ui.execute(null, null);
        } catch (AssertionError e) {
            assertNotNull(e);
        }

    }
}
