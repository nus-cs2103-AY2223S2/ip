package rick.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents a test suite that tests all commands that insert tasks in the
 * App's storage:
 * <ul>
 *     <li>{@code DeadlineCommand}</li>
 *     <li>{@code EventCommand}</li>
 *     <li>{@code TodoCommand}</li>
 * </ul>
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class TaskCommandTest extends CommandTest {
    /**
     * Tests a valid {@code TodoCommand} class.
     */
    @Test
    public void testValidTodo() {
        TodoCommand todo = new TodoCommand("val");
        long beforeSize = STORAGE.size();
        String actualUi = todo.execute(TASK_LIST, UI);
        STORAGE.delete(Integer.parseInt(String.valueOf(STORAGE.size() - 1)));
        String expectedUi = "Got it. I've added this task:\n"
                + "  [T][ ] val\n"
                + String.format("Now you have %s task%s in the list.",
                    beforeSize + 1,
                    beforeSize + 1 == 1 ? "" : "s");
        assertEquals(expectedUi, actualUi);
    }

    /**
     * Tests an invalid {@code TodoCommand} class.
     * This occurs when an empty description is provided.
     */
    @Test
    public void testInvalidTodo() {
        Command todo = new TodoCommand();
        String actualUi = todo.execute(TASK_LIST, UI);
        String expectedUi = "The description of a Todo cannot be empty.";
        assertEquals(expectedUi, actualUi);
    }

    /**
     * Test the {@code DeadlineCommand} class.
     */
    @Test
    public void testValidDeadline() {
        Command deadline = new DeadlineCommand(
                "task one /by 2/2/23 0000"
        );
        long beforeSize = STORAGE.size();
        String actualUi = deadline.execute(TASK_LIST, UI);
        STORAGE.delete(Integer.parseInt(String.valueOf(beforeSize)));

        String expectedUi = "Got it. I've added this task:\n"
                + "  [D][ ] task one (by: Feb 02 2023 12:00AM)\n"
                + String.format("Now you have %s task%s in the list.",
                    beforeSize + 1,
                    beforeSize + 1 == 1 ? "" : "s");
        assertEquals(expectedUi, actualUi);
    }

    /**
     * Tests an invalid {@code DeadlineCommand} class.
     * This is triggered by one of the following:
     * <ul>
     *     <li>an empty description is provided</li>
     *     <li>an invalid format is used</li>
     *     <li>an invalid date is provided</li>
     * </ul>
     */
    @Test
    public void testInvalidDeadline() {
        Command deadlineInvalidUsage = new DeadlineCommand(
                "task one"
        );
        Command deadlineInvalidDate = new DeadlineCommand(
                "task one /by 2-2-23 12am"
        );

        Command deadlineEmpty = new DeadlineCommand();

        String actualUiOne = deadlineInvalidUsage.execute(TASK_LIST, UI);
        String expectedUiOne = "Usage: deadline {task} /by {deadline}";
        assertEquals(expectedUiOne, actualUiOne);

        String actualUiTwo = deadlineInvalidDate.execute(TASK_LIST, UI);
        String expectedUiTwo = "An invalid date was entered. Please use this format:\n"
                + "{day}/{month}/{year} {hour}{minute}\n"
                + "Example: 2/2/23 1200";
        assertEquals(expectedUiTwo, actualUiTwo);

        String actualUiThree = deadlineEmpty.execute(TASK_LIST, UI);
        String expectedUiThree =
                "The description of a Deadline cannot be empty.";
        assertEquals(expectedUiThree, actualUiThree);
    }

    /**
     * Test the {@code EventCommand} class.
     */
    @Test
    public void testValidEvent() {
        Command event = new EventCommand(
                "task one /from 2/2/23 0000 /to 2/2/23 0001"
        );

        long beforeSize = STORAGE.size();
        String actualUi = event.execute(TASK_LIST, UI);
        STORAGE.delete(Integer.parseInt(String.valueOf(beforeSize)));
        String expectedUi = "Got it. I've added this task:\n"
                + "  [E][ ] task one (from: Feb 2 2023 12:00AM to: Feb 2 2023 12:01AM)\n"
                + String.format("Now you have %s task%s in the list.",
                    beforeSize + 1,
                    beforeSize + 1 == 1 ? "" : "s");
        assertEquals(expectedUi, actualUi);
    }

    /**
     * Tests an invalid {@code EventCommand} class.
     * This is triggered by one of the following:
     * <ul>
     *     <li>an empty description is provided</li>
     *     <li>an invalid format is used</li>
     *     <li>an invalid date is provided</li>
     * </ul>
     */
    @Test
    public void testInvalidEvent() {
        Command eventWrongFormat = new EventCommand(
                "task one"
        );

        Command eventImproperDate = new EventCommand(
                "task one /from 2-2-23 12am /to 2-2-23 1am"
        );

        Command eventEmpty = new EventCommand();

        String expectedUiOne = "Usage: event {task} /from {start} /to {end}";
        String actualUiOne = eventWrongFormat.execute(TASK_LIST, UI);
        assertEquals(expectedUiOne, actualUiOne);

        String expectedUiTwo = "An invalid date was entered. Please use this format:\n"
                + "{day}/{month}/{year} {hour}{minute}\n"
                + "Example: 2/2/23 1200";
        String actualUiTwo = eventImproperDate.execute(TASK_LIST, UI);
        assertEquals(expectedUiTwo, actualUiTwo);

        String expectedUiThree = "The description of a Event cannot be empty.";

        String actualUiThree = eventEmpty.execute(TASK_LIST, UI);
        assertEquals(expectedUiThree, actualUiThree);
    }
}
