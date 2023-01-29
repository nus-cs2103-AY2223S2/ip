package rick.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests all commands that manipulate tasks in the App's storage:
 * <ul>
 *     <li>{@code DeleteCommand}</li>
 *     <li>{@code MarkCommand}</li>
 *     <li>{@code UnmarkCommand}</li>
 * </ul>
 * Also tests the {@code TaskListInvalidAccessException} for when a user
 * attempts to manipulate a task in the app with the wrong numeric Index.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class ManipulateCommandTest extends CommandTest {
    /**
     * Tests the {@code MarkCommand} class.
     */
    @Test
    public void testMark() {
        Command todo = new TodoCommand("task one");
        long size = storage.size();
        todo.execute(taskList, ui);

        Command mark = new MarkCommand(Integer.parseInt(String.valueOf(size + 1)));
        String actualUi = mark.execute(taskList, ui);
        storage.delete(Integer.parseInt(String.valueOf(size)));
        String expectedUi = "Nice! I've marked this task as done:\n" +
                "  [T][X] task one";
        assertEquals(expectedUi, actualUi);
    }

    /**
     * Tests the {@code UnmarkCommand} class.
     */
    @Test
    public void testUnmark() {
        Command todo = new TodoCommand("task one");
        int size = Integer.parseInt(String.valueOf(storage.size()));
        todo.execute(taskList, ui);

        Command unmark = new UnmarkCommand(size);
        String actualUi = unmark.execute(taskList, ui);
        storage.delete(size);
        String expectedUi = "OK, I've marked this task as not done yet:\n" +
                "  [T][ ] task one";

        assertEquals(expectedUi, actualUi);
    }

    /**
     * Tests the {@code DeleteCommand} class.
     */
    @Test
    public void testDelete() {
        Command todo = new TodoCommand("task one");
        int size = Integer.parseInt(String.valueOf(storage.size()));
        todo.execute(taskList, ui);


        Command delete = new DeleteCommand(size + 1);
        String actualUi = delete.execute(taskList, ui);
        String expectedUi = "Noted. I've removed this task:\n" +
                "  [T][ ] task one\n" +
                String.format("Now you have %s task%s in the list.", size, size == 1 ? "": "s");

        assertEquals(expectedUi, actualUi);
    }

    /**
     * Tests the {@code TaskListInvalidAccessException} class.
     */
    @Test
    public void testInvalidIndex() {
        int beforeSize = Integer.parseInt(String.valueOf(storage.size()));
        Command todo = new TodoCommand("task one");
        todo.execute(taskList, ui);

        Command invalidIndex = new MarkCommand(beforeSize + 2);
        storage.delete(beforeSize);
        String actualUi = invalidIndex.execute(taskList, ui);
        String expectedUi = "An invalid index was entered. Please try again.";

        assertEquals(expectedUi, actualUi);
    }
}
