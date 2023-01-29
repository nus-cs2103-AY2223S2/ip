package rick.command;

import rick.RickUtils;
import rick.Parser;
import rick.exceptions.TaskListFullException;
import rick.task.DeadlineTask;
import rick.task.RickTask;
import rick.task.TodoTask;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests all commands that query tasks in the App's storage:
 * <ul>
 *     <li>{@code DateFilterCommand}</li>
 *     <li>{@code FindCommand}</li>
 *     <li>{@code ListCommand}</li>
 * </ul>
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class QueryCommandTest extends CommandTest {
    private static final ArrayList<RickTask> currentTasks = new ArrayList<>();

    /**
     * Temporarily stores existing tasks in the Storage to isolate them from
     * the test environment.
     */
    @BeforeAll
    public static void clearStorage() {
        int size = Integer.parseInt(String.valueOf(storage.size()));
        while (size > 0) {
            currentTasks.add(storage.delete(size - 1));
            size -= 1;
        }
    }

    /**
     * Repopulates the storage and reinstates its original state.
     */
    @AfterAll
    public static void repopulate() {
        while (currentTasks.size() > 0) {
            try {
                taskList.add(currentTasks.remove(currentTasks.size() - 1));
            } catch (TaskListFullException e) {
                return;
            }
        }
    }

    /**
     * Tests the {@code ListCommand} class.
     */
    @Test
    public void testList() {
        for (int i = 0; i < 5; i++) {
            try {
                taskList.add(new TodoTask("Task " + (i + 1)));
            } catch (TaskListFullException e) {
                break;
            }
        }

        Command list = new ListCommand();
        String actual = list.execute(taskList, ui);
        for (int i = 5; i > 0; i--) {
            storage.delete(i - 1);
        }
        String expectedUi = "1. [T][ ] Task 1\n" +
                "2. [T][ ] Task 2\n" +
                "3. [T][ ] Task 3\n" +
                "4. [T][ ] Task 4\n" +
                "5. [T][ ] Task 5";

        assertEquals(expectedUi, actual);
    }

    /**
     * Tests the {@code DateFilterCommand} class.
     */
    @Test
    public void testValidDateFilter() {
        for (int i = 0; i < 5; i++) {
            RickTask dl = new DeadlineTask(
                    "deadline " + (i + 1),
                    RickUtils.parseDateTime(String.format("2/2/23 000%s", i))
            );
            storage.write(dl.toDbSchema());
        }

        RickTask dlTwo = new DeadlineTask(
                "deadline " + (6),
                RickUtils.parseDateTime("1/2/23 0000"));
        storage.write(dlTwo.toDbSchema());

        Command validFilter = new DateFilterCommand("/on 2/2/23");
        String actualValidUi = validFilter.execute(taskList, ui);

        for (int i = 6; i > 0; i--) {
            storage.delete(i - 1);
        }

        String expectedValidUi = "Searching for a list of tasks occurring on 2 Feb 2023:\n" +
                "- [D][ ] deadline 1 (by: Feb 02 2023 12:00AM)\n" +
                "- [D][ ] deadline 2 (by: Feb 02 2023 12:01AM)\n" +
                "- [D][ ] deadline 3 (by: Feb 02 2023 12:02AM)\n" +
                "- [D][ ] deadline 4 (by: Feb 02 2023 12:03AM)\n" +
                "- [D][ ] deadline 5 (by: Feb 02 2023 12:04AM)";

        assertEquals(expectedValidUi, actualValidUi);
    }

    /**
     * Tests the {@code RickInvalidDateException} class in tandem with the
     * {@code DateFilterCommand} class.
     * This scenario occurs when an invalid date or command is provided to the
     * {@code DateFilterCommand} class.
     */
    @Test
    public void testInvalidDateFilter() {
        Command invalidDateFilter = new DateFilterCommand("/on 2-2-23");
        Command wrongFormat = new DateFilterCommand("2/2/23");
        Command emptyDate = new DateFilterCommand("/on 1/2/23");

        String expectedUiOne = "An invalid date was entered. Please use this format:\n" +
                        "{day}/{month}/{year} {hour}{minute}\n" +
                        "Example: 2/2/23 1200";
        String expectedUiTwo =
                "Usage: tasks /on {day}/{month}/{year}";
        String expectedUiThree = "Searching for a list of tasks occurring on 1 Feb 2023:\n" +
                "Hooray. No tasks occur on this date.";

        String actualUiOne = invalidDateFilter.execute(taskList, ui);
        assertEquals(expectedUiOne, actualUiOne);

        String actualUiTwo = wrongFormat.execute(taskList, ui);
        assertEquals(expectedUiTwo, actualUiTwo);

        String actualUiThree = emptyDate.execute(taskList, ui);
        assertEquals(expectedUiThree, actualUiThree);
    }

    /**
     * Tests the {@code FindCommand} class.
     */
    @Test
    public void testFind() {
        storage.write(new TodoTask("book 1").toDbSchema());
        storage.write(new TodoTask("paper 2").toDbSchema());
        storage.write(new TodoTask("read book now").toDbSchema());

        Command search = new FindCommand("book");
        String actualValidUi = search.execute(taskList, ui);
        for (int i = 3; i > 0; i--) {
            storage.delete(i - 1);
        }

        String expectedValidUi = "Here are the matching tasks in your list:\n" +
                "1. [T][ ] book 1\n" +
                "2. [T][ ] read book now";
        assertEquals(expectedValidUi, actualValidUi);

        Command searchNoResult = new FindCommand("write");
        String actualNoResUi = searchNoResult.execute(taskList, ui);
        String expectedNoResUi = "Here are the matching tasks in your list:\n" +
                "Try again. No tasks have this term.";
        assertEquals(expectedNoResUi, actualNoResUi);

        Command emptySearch = Parser.parse("find");
        String actualEmptyUi = emptySearch.execute(taskList, ui);
        String expectedEmptyUi = "An empty search was attempted. Valid Usage: find {search term}";
        assertEquals(expectedEmptyUi, actualEmptyUi);
    }
}
