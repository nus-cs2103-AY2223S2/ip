package rick.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
 *         AY2223-S2 CS2103T
 */
public class ManipulateCommandTest extends CommandTest {
    /**
     * Tests the {@code MarkCommand} class.
     */
    @Test
    public void testMark() {
        Command todo = new TodoCommand("task one");
        long size = STORAGE.size();
        todo.execute(TASK_LIST, UI);

        Command mark = new MarkCommand(Integer.parseInt(String.valueOf(size + 1)));
        String actualUi = mark.execute(TASK_LIST, UI);
        STORAGE.delete(Integer.parseInt(String.valueOf(size)));
        String expectedUi = "Nice! I've marked this task as done:\n"
                + "  [T][X] task one";
        assertEquals(expectedUi, actualUi);
    }

    /**
     * Tests the {@code UnmarkCommand} class.
     */
    @Test
    public void testUnmark() {
        Command todo = new TodoCommand("task one");
        int size = Integer.parseInt(String.valueOf(STORAGE.size()));
        todo.execute(TASK_LIST, UI);

        Command unmark = new UnmarkCommand(size + 1);
        String actualUi = unmark.execute(TASK_LIST, UI);
        STORAGE.delete(size);
        String expectedUi = "OK, I've marked this task as not done yet:\n"
                + "  [T][ ] task one";

        assertEquals(expectedUi, actualUi);
    }

    /**
     * Tests the {@code DeleteCommand} class.
     */
    @Test
    public void testDelete() {
        Command todo = new TodoCommand("task one");
        int size = Integer.parseInt(String.valueOf(STORAGE.size()));
        todo.execute(TASK_LIST, UI);


        Command delete = new DeleteCommand(size + 1);
        String actualUi = delete.execute(TASK_LIST, UI);
        String expectedUi = "Noted. I've removed this task:\n"
                + "  [T][ ] task one\n"
                + String.format("Now you have %s task%s in the list.", size, size == 1 ? "" : "s");

        assertEquals(expectedUi, actualUi);
    }

    /**
     * Tests the {@code TaskListInvalidAccessException} class.
     */
    @Test
    public void testInvalidIndex() {
        int beforeSize = Integer.parseInt(String.valueOf(STORAGE.size()));
        Command todo = new TodoCommand("task one");
        todo.execute(TASK_LIST, UI);

        Command invalidIndex = new MarkCommand(beforeSize + 2);
        STORAGE.delete(beforeSize);
        String actualUi = invalidIndex.execute(TASK_LIST, UI);
        String expectedUi = "An invalid index was entered. Please try again.";

        assertEquals(expectedUi, actualUi);
    }
}
