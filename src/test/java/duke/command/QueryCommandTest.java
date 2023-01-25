package duke.command;

import duke.DukeUtils;
import duke.exceptions.TaskListFullException;
import duke.task.DeadlineTask;
import duke.task.DukeTask;
import duke.task.DukeTaskTest;
import duke.task.TodoTask;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryCommandTest extends CommandTest {
    private static ArrayList<DukeTask> currentTasks = new ArrayList<DukeTask>();
    @BeforeAll
    public static void clearStorage() {
        int size = Integer.parseInt(String.valueOf(s.size()));
        while (size > 0) {
            currentTasks.add(s.delete(size - 1));
            size -= 1;
        }
    }

    @AfterAll
    public static void repopulate() {
        while (currentTasks.size() > 0) {
            try {
                ts.add(currentTasks.remove(currentTasks.size() - 1));
            } catch (TaskListFullException e) {
                return;
            }
        }
    }

    @Test
    public void testList() {
        for (int i = 0; i < 5; i++) {
            try {
                ts.add(new TodoTask("Task " + (i + 1)));
            } catch (TaskListFullException e) {
                break;
            }
        }
        outContent.reset();
        Command list = new ListCommand();
        list.execute(ts, ui);
        for (int i = 5; i > 0; i--) {
            s.delete(i - 1);
        }
        String expectedUI =
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
                expectedUI, actual
        );
    }

    @Test
    public void testValidDateFilter() {
        for (int i = 0; i < 5; i++) {
            DukeTask dl = new DeadlineTask(
                    "deadline " + (i + 1),
                    DukeUtils.parseDateTime(String.format("2/2/23 000%s", i))
            );
            s.write(dl.toDBSchema());
        }

        DukeTask dlTwo = new DeadlineTask(
                "deadline " + (6),
                DukeUtils.parseDateTime("1/2/23 0000"));
        s.write(dlTwo.toDBSchema());
        outContent.reset();

        Command validFilter = new DateFilterCommand("/on 2/2/23");
        validFilter.execute(ts, ui);

        for (int i = 6; i > 0; i--) {
            s.delete(i - 1);
        }

        String expectedValidUI =
                "      ____________________________________________________________\n" +
                "Rick: Searching for a list of tasks occurring on 2 Feb 2023:\n" +
                "      - [D][ ] deadline 1 (by: Feb 02 2023 12:00AM)\n" +
                "      - [D][ ] deadline 2 (by: Feb 02 2023 12:01AM)\n" +
                "      - [D][ ] deadline 3 (by: Feb 02 2023 12:02AM)\n" +
                "      - [D][ ] deadline 4 (by: Feb 02 2023 12:03AM)\n" +
                "      - [D][ ] deadline 5 (by: Feb 02 2023 12:04AM)\n" +
                "      ____________________________________________________________\n\n";
        String actualValidUI = outContent.toString();
        outContent.reset();
        assertEquals(expectedValidUI, actualValidUI);
    }

    @Test
    public void testInvalidDateFilter() {
        Command invalidDateFilter = new DateFilterCommand("/on 2-2-23");
        Command wrongFormat = new DateFilterCommand("2/2/23");
        Command emptyDate = new DateFilterCommand("/on 1/2/23");

        String ui1 =
                "      ____________________________________________________________\n" +
                "Rick: An invalid date was entered. Please use this format:\n" +
                "      {day}/{month}/{year} {hour}{minute}\n" +
                "      Example: 2/2/23 1200\n" +
                "      ____________________________________________________________\n\n";
        String ui2 =
                "      ____________________________________________________________\n" +
                "Rick: Usage: tasks /on {day}/{month}/{year}\n" +
                "      ____________________________________________________________\n\n";
        String ui3 =
                "      ____________________________________________________________\n" +
                "Rick: Searching for a list of tasks occurring on 1 Feb 2023:\n" +
                "      Hooray. No tasks occur on this date.\n" +
                "      ____________________________________________________________\n\n";

        invalidDateFilter.execute(ts, ui);
        String ac1 = outContent.toString();
        outContent.reset();
        assertEquals(ui1, ac1);

        wrongFormat.execute(ts, ui);
        String ac2 = outContent.toString();
        outContent.reset();
        assertEquals(ui2, ac2);

        emptyDate.execute(ts, ui);
        String ac3 = outContent.toString();
        outContent.reset();
        assertEquals(ui3, ac3);

    }
}
