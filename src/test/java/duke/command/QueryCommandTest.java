package duke.command;

import duke.DukeUtils;
import duke.Parser;
import duke.exceptions.TaskListFullException;
import duke.task.DeadlineTask;
import duke.task.DukeTask;
import duke.task.TodoTask;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryCommandTest extends CommandTest {
    private static final ArrayList<DukeTask> currentTasks = new ArrayList<>();
    @BeforeAll
    public static void clearStorage() {
        int size = Integer.parseInt(String.valueOf(storage.size()));
        while (size > 0) {
            currentTasks.add(storage.delete(size - 1));
            size -= 1;
        }
    }

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

    @Test
    public void testList() {
        for (int i = 0; i < 5; i++) {
            try {
                taskList.add(new TodoTask("Task " + (i + 1)));
            } catch (TaskListFullException e) {
                break;
            }
        }
        outContent.reset();
        Command list = new ListCommand();
        list.execute(taskList, ui);
        for (int i = 5; i > 0; i--) {
            storage.delete(i - 1);
        }
        String expectedUi =
                "      ____________________________________________________________\n" +
                "Rick: 1. [T][ ] Task 1\n" +
                "      2. [T][ ] Task 2\n" +
                "      3. [T][ ] Task 3\n" +
                "      4. [T][ ] Task 4\n" +
                "      5. [T][ ] Task 5\n" +
                "      ____________________________________________________________\n\n";
        String actual = outContent.toString();
        outContent.reset();
        assertEquals(
                expectedUi, actual
        );
    }

    @Test
    public void testValidDateFilter() {
        for (int i = 0; i < 5; i++) {
            DukeTask dl = new DeadlineTask(
                    "deadline " + (i + 1),
                    DukeUtils.parseDateTime(String.format("2/2/23 000%s", i))
            );
            storage.write(dl.toDbSchema());
        }

        DukeTask dlTwo = new DeadlineTask(
                "deadline " + (6),
                DukeUtils.parseDateTime("1/2/23 0000"));
        storage.write(dlTwo.toDbSchema());
        outContent.reset();

        Command validFilter = new DateFilterCommand("/on 2/2/23");
        validFilter.execute(taskList, ui);

        for (int i = 6; i > 0; i--) {
            storage.delete(i - 1);
        }

        String expectedValidUi =
                "      ____________________________________________________________\n" +
                "Rick: Searching for a list of tasks occurring on 2 Feb 2023:\n" +
                "      - [D][ ] deadline 1 (by: Feb 02 2023 12:00AM)\n" +
                "      - [D][ ] deadline 2 (by: Feb 02 2023 12:01AM)\n" +
                "      - [D][ ] deadline 3 (by: Feb 02 2023 12:02AM)\n" +
                "      - [D][ ] deadline 4 (by: Feb 02 2023 12:03AM)\n" +
                "      - [D][ ] deadline 5 (by: Feb 02 2023 12:04AM)\n" +
                "      ____________________________________________________________\n\n";
        String actualValidUi = outContent.toString();
        outContent.reset();
        assertEquals(expectedValidUi, actualValidUi);
    }

    @Test
    public void testInvalidDateFilter() {
        Command invalidDateFilter = new DateFilterCommand("/on 2-2-23");
        Command wrongFormat = new DateFilterCommand("2/2/23");
        Command emptyDate = new DateFilterCommand("/on 1/2/23");

        String expectedUiOne =
                "      ____________________________________________________________\n" +
                        "Rick: An invalid date was entered. Please use this format:\n" +
                        "      {day}/{month}/{year} {hour}{minute}\n" +
                        "      Example: 2/2/23 1200\n" +
                        "      ____________________________________________________________\n\n";
        String expectedUiTwo =
                "      ____________________________________________________________\n" +
                        "Rick: Usage: tasks /on {day}/{month}/{year}\n" +
                        "      ____________________________________________________________\n\n";
        String expectedUiThree =
                "      ____________________________________________________________\n" +
                        "Rick: Searching for a list of tasks occurring on 1 Feb 2023:\n" +
                        "      Hooray. No tasks occur on this date.\n" +
                        "      ____________________________________________________________\n\n";

        invalidDateFilter.execute(taskList, ui);
        String actualUiOne = outContent.toString();
        outContent.reset();
        assertEquals(expectedUiOne, actualUiOne);

        wrongFormat.execute(taskList, ui);
        String actualUiTwo = outContent.toString();
        outContent.reset();
        assertEquals(expectedUiTwo, actualUiTwo);

        emptyDate.execute(taskList, ui);
        String actualUiThree = outContent.toString();
        outContent.reset();

        assertEquals(expectedUiThree, actualUiThree);
    }

    @Test
    public void testFind() {
        storage.write(new TodoTask("book 1").toDbSchema());
        storage.write(new TodoTask("paper 2").toDbSchema());
        storage.write(new TodoTask("read book now").toDbSchema());

        Command search = new FindCommand("book");
        search.execute(taskList, ui);
        for (int i = 3; i > 0; i--) {
            storage.delete(i - 1);
        }

        String expectedValidUi =
                "      ____________________________________________________________\n" +
                "Rick: Here are the matching tasks in your list:\n" +
                "      1. [T][ ] book 1\n" +
                "      2. [T][ ] read book now\n" +
                "      ____________________________________________________________\n\n";
        String actualValidUi = outContent.toString();
        outContent.reset();
        assertEquals(expectedValidUi, actualValidUi);

        Command searchNoResult = new FindCommand("write");
        searchNoResult.execute(taskList, ui);
        String expectedNoResUi =
                "      ____________________________________________________________\n" +
                "Rick: Here are the matching tasks in your list:\n" +
                "      Try again. No tasks have this term.\n" +
                "      ____________________________________________________________\n\n";
        String actualNoResUi = outContent.toString();
        outContent.reset();
        assertEquals(expectedNoResUi, actualNoResUi);

        Command emptySearch = Parser.parse("find");
        emptySearch.execute(taskList, ui);
        String expectedEmptyUi =
                "      ____________________________________________________________\n" +
                "Rick: An empty search was attempted. Valid Usage: find {search term}\n" +
                "      ____________________________________________________________\n\n";
        String actualEmptyUi = outContent.toString();
        outContent.reset();
        assertEquals(expectedEmptyUi, actualEmptyUi);
    }
}
