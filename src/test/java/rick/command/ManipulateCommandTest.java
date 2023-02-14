package rick.command;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import rick.Parser;

/**
 * Represents a test suite that tests all commands that manipulate tasks in the
 * App's storage:
 * <ul>
 *     <li>{@code DeleteCommand},</li>
 *     <li>{@code MarkCommand},</li>
 *     <li>{@code UnmarkCommand},</li>
 * </ul>
 * and also tests the {@code TaskListInvalidAccessException} for when a user
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
        String expectedUi = "Nice! I've marked this task as done:\n"
                + "  [T][X] task one";

        Command emptyMark = Parser.parse("mark");
        String actualUiEmpty = emptyMark.execute(TASK_LIST, UI);
        String expectedUiEmpty = "An index was not provided for the mark action. "
                + "Usage: mark {index}";

        STORAGE.delete(Integer.parseInt(String.valueOf(size)));
        assertAll(() -> assertEquals(expectedUi, actualUi
            ), () -> assertEquals(expectedUiEmpty, actualUiEmpty)
        );
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
        String expectedUi = "OK, I've marked this task as not done yet:\n"
                + "  [T][ ] task one";

        Command emptyUnmark = Parser.parse("unmark");
        String actualUiEmpty = emptyUnmark.execute(TASK_LIST, UI);
        String expectedUiEmpty = "An index was not provided for the unmark action. "
                + "Usage: unmark {index}";

        STORAGE.delete(size);

        assertAll(() -> assertEquals(expectedUi, actualUi
                ), () -> assertEquals(expectedUiEmpty, actualUiEmpty)
        );
    }

    /**
     * Tests the {@code DeleteCommand} class.
     */
    @Test
    public void testDelete() {
        Command todo = new TodoCommand("task one");
        int size = Integer.parseInt(String.valueOf(STORAGE.size()));
        todo.execute(TASK_LIST, UI);


        Command emptyDelete = Parser.parse("delete");
        String actualUiEmpty = emptyDelete.execute(TASK_LIST, UI);
        String expectedUiEmpty = "An index was not provided for the delete action. "
                + "Usage: delete {index}";

        Command delete = new DeleteCommand(size + 1);
        String actualUi = delete.execute(TASK_LIST, UI);
        String expectedUi = "Noted. I've removed this task:\n"
                + "  [T][ ] task one\n"
                + String.format("Now you have %s task%s in the list.", size, size == 1 ? "" : "s");

        assertAll(() -> assertEquals(expectedUi, actualUi
            ), () -> assertEquals(expectedUiEmpty, actualUiEmpty)
        );
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
        String actualUi = invalidIndex.execute(TASK_LIST, UI);
        String expectedUi = "An invalid index was entered. Please try again.";

        STORAGE.delete(beforeSize);
        assertEquals(expectedUi, actualUi);
    }

    /**
     * Tests the {@code MultiManipulateCommand} class, when multiple
     * whitespace-separated indices are provided.
     */
    @Test
    public void testMultipleIndexManipulate() {
        Parser.setTaskList(TASK_LIST);
        for (int i = 0; i < 5; i++) {
            Command todo = new TodoCommand("Command: " + (i + 1));
            todo.execute(TASK_LIST, UI);
        }
        Command validMultiIndexCommand = Parser.parse("mark 1 3 5");
        String actualUiValidIndex = validMultiIndexCommand.execute(TASK_LIST, UI);
        String expectedUiValidIndex = "Got it. I've marked these 3 tasks as done:\n"
                + "[T][X] Command: 1\n"
                + "[T][X] Command: 3\n"
                + "[T][X] Command: 5";

        //6 is invalid
        Command invalidMultiIndexCommand = Parser.parse("unmark 2 4 6");
        String actualUiInvalidIndex = invalidMultiIndexCommand.execute(TASK_LIST, UI);
        String expectedUiInvalidIndex = "Got it. I've marked these 2 tasks as not completed:\n"
                + "[T][ ] Command: 2\n"
                + "[T][ ] Command: 4\n"
                + " \n"
                + "These 1 error(s) occurred:\n"
                + "An invalid index `6` was provided. Ensure a proper index is provided.";

        Command allIndexesInvalidCommand = Parser.parse("mark 7 8 9");
        String actualUiAllInvalidIndex = allIndexesInvalidCommand.execute(TASK_LIST, UI);
        String expectedUiAllInvalidIndex = "Sorry. No tasks were successfully modified.\n"
                + " \n"
                + "These 3 error(s) occurred:\n"
                + "An invalid index `7` was provided. Ensure a proper index is provided.\n"
                + "An invalid index `8` was provided. Ensure a proper index is provided.\n"
                + "An invalid index `9` was provided. Ensure a proper index is provided.";

        Command invalidDigitCommand = Parser.parse("mark 1 2t 3");
        String actualUiInvalidDigit = invalidDigitCommand.execute(TASK_LIST, UI);
        String expectedUiInvalidDigit = "An invalid option was used for the mark command, "
                + "or invalid indexes were provided.\n"
                + "Please try again.";

        for (int i = 0; i < 5; i++) {
            STORAGE.delete(0);
        }

        assertAll(() -> assertEquals(expectedUiValidIndex, actualUiValidIndex
                ), () -> assertEquals(expectedUiInvalidIndex, actualUiInvalidIndex
                ), () -> assertEquals(expectedUiAllInvalidIndex, actualUiAllInvalidIndex
                ), () -> assertEquals(expectedUiInvalidDigit, actualUiInvalidDigit)
        );
    }

    /**
     * Tests the {@code MultiManipulateCommand} class, when a range of indexes
     * are provided.
     */
    @Test
    public void testRangeManipulate() {
        Parser.setTaskList(TASK_LIST);
        for (int i = 1; i <= 5; i++) {
            new TodoCommand("Command: " + i).execute(TASK_LIST, UI);
        }

        Command validRangeHyphen = Parser.parse("mark 1 - 5");
        String actualUiValidRangeHyphen = validRangeHyphen.execute(TASK_LIST, UI);
        String expectedUiValidRangeHyphen = "Got it. I've marked these 5 tasks as done:\n"
                + "[T][X] Command: 1\n"
                + "[T][X] Command: 2\n"
                + "[T][X] Command: 3\n"
                + "[T][X] Command: 4\n"
                + "[T][X] Command: 5";

        Command validRangeHyphenNoSpace = Parser.parse("unmark 1-5");
        String actualUiValidRangeHyphenNoSpace = validRangeHyphenNoSpace.execute(TASK_LIST, UI);
        String expectedUiValidRangeHyphenNoSpace = "Got it. I've marked these 5 tasks as not completed:\n"
                + "[T][ ] Command: 1\n"
                + "[T][ ] Command: 2\n"
                + "[T][ ] Command: 3\n"
                + "[T][ ] Command: 4\n"
                + "[T][ ] Command: 5";

        Command validRangeTo = Parser.parse("mark 1 to 5");
        String actualUiValidRangeTo = validRangeTo.execute(TASK_LIST, UI);
        String expectedUiValidRangeTo = "Got it. I've marked these 5 tasks as done:\n"
                + "[T][X] Command: 1\n"
                + "[T][X] Command: 2\n"
                + "[T][X] Command: 3\n"
                + "[T][X] Command: 4\n"
                + "[T][X] Command: 5";

        Command validRangeToNoSpace = Parser.parse("unmark 1to5");
        String actualUiValidRangeToNoSpace = validRangeToNoSpace.execute(TASK_LIST, UI);
        String expectedUiValidRangeToNoSpace = "Got it. I've marked these 5 tasks as not completed:\n"
                + "[T][ ] Command: 1\n"
                + "[T][ ] Command: 2\n"
                + "[T][ ] Command: 3\n"
                + "[T][ ] Command: 4\n"
                + "[T][ ] Command: 5";

        Command invalidRange = Parser.parse("mark 1 to 6");
        String actualUiInvalidRange = invalidRange.execute(TASK_LIST, UI);
        String expectedUiInvalidRange = "Got it. I've marked these 5 tasks as done:\n"
                + "[T][X] Command: 1\n"
                + "[T][X] Command: 2\n"
                + "[T][X] Command: 3\n"
                + "[T][X] Command: 4\n"
                + "[T][X] Command: 5\n"
                + " \n"
                + "These 1 error(s) occurred:\n"
                + "An invalid index `6` was provided. Ensure a proper index is provided.";

        Command invalidRangeLarge = Parser.parse("mark 6 to 101");
        String actualUiInvalidRangeLarge = invalidRangeLarge.execute(TASK_LIST, UI);
        String expectedUiInvalidRangeLarge = "Sorry. No tasks were successfully modified.\n"
                + " \n"
                + "These 96 error(s) occurred:\n"
                + "An invalid index `6` was provided. Ensure a proper index is provided.\n"
                + "An invalid index `7` was provided. Ensure a proper index is provided.\n"
                + "An invalid index `8` was provided. Ensure a proper index is provided.\n"
                + "An invalid index `9` was provided. Ensure a proper index is provided.\n"
                + "An invalid index `10` was provided. Ensure a proper index is provided.";

        Command startGteEnd = Parser.parse("mark 2 to 1");
        String actualUiGteEnd = startGteEnd.execute(TASK_LIST, UI);
        String expectedUiGteEnd = "An invalid range of indexes was entered for the mark command.\n"
                + "Please ensure the start index is lesser than or equal to the end index.";

        for (int i = 0; i < 5; i++) {
            STORAGE.delete(0);
        }

        assertAll(() -> assertEquals(expectedUiValidRangeHyphen, actualUiValidRangeHyphen
                ), () -> assertEquals(expectedUiValidRangeHyphenNoSpace, actualUiValidRangeHyphenNoSpace
                ), () -> assertEquals(expectedUiValidRangeTo, actualUiValidRangeTo
                ), () -> assertEquals(expectedUiValidRangeToNoSpace, actualUiValidRangeToNoSpace
                ), () -> assertEquals(expectedUiInvalidRange, actualUiInvalidRange
                ), () -> assertEquals(expectedUiInvalidRangeLarge, actualUiInvalidRangeLarge
                ), () -> assertEquals(expectedUiGteEnd, actualUiGteEnd)
        );
    }

    /**
     * Tests the {@code MultiManipulateCommand} class, when dates are provided.
     */
    @Test
    public void testDateManipulate() {
        Parser.setTaskList(TASK_LIST);
        for (int i = 1; i <= 3; i++) {
            new DeadlineCommand("Deadline: "
                + i
                + " /by 2/2/23 1200"
            ).execute(TASK_LIST, UI);
        }
        for (int i = 1; i <= 2; i++) {
            new EventCommand("Event: "
                + i
                + " /from 2/2/23 1200 /to 3/2/23 1200"
            ).execute(TASK_LIST, UI);
        }

        Command validDate = Parser.parse("mark /on 2/2/23");
        String actualUiValidDate = validDate.execute(TASK_LIST, UI);
        String expectedUiValidDate = "Got it. I've marked these 5 tasks as done:\n"
                + "[D][X] Deadline: 1 (by: Feb 02 2023 12:00PM)\n"
                + "[D][X] Deadline: 2 (by: Feb 02 2023 12:00PM)\n"
                + "[D][X] Deadline: 3 (by: Feb 02 2023 12:00PM)\n"
                + "[E][X] Event: 1 (from: Feb 2 2023 12:00PM to: Feb 3 2023 12:00PM)\n"
                + "[E][X] Event: 2 (from: Feb 2 2023 12:00PM to: Feb 3 2023 12:00PM)";

        Command validDateHyphen = Parser.parse("unmark -o 3/2/23");
        String actualUiHyphen = validDateHyphen.execute(TASK_LIST, UI);
        String expectedUiHyphen = "Got it. I've marked these 2 tasks as not completed:\n"
                + "[E][ ] Event: 1 (from: Feb 2 2023 12:00PM to: Feb 3 2023 12:00PM)\n"
                + "[E][ ] Event: 2 (from: Feb 2 2023 12:00PM to: Feb 3 2023 12:00PM)";

        Command invalidDateHyphen = Parser.parse("mark -o 03/03/2023");
        String actualUiInvalidDateHyphen = invalidDateHyphen.execute(TASK_LIST, UI);
        String expectedUiInvalidDateHyphen = "An invalid date was supplied.\n"
                + "Ensure it is of the format d/m/yy.\n"
                + "Example: 2/2/23 OR 02/02/23";

        Command emptyDate = Parser.parse("mark --o 4/2/23");
        String actualUiEmptyDate = emptyDate.execute(TASK_LIST, UI);
        String expectedUiEmptyDate = "Hooray! No tasks occur on that date.";

        for (int i = 0; i < 5; i++) {
            STORAGE.delete(0);
        }

        assertAll(() -> assertEquals(expectedUiValidDate, actualUiValidDate
                ), () -> assertEquals(expectedUiHyphen, actualUiHyphen
                ), () -> assertEquals(expectedUiInvalidDateHyphen, actualUiInvalidDateHyphen
                ), () -> assertEquals(expectedUiEmptyDate, actualUiEmptyDate)
        );
    }

    /**
     * Tests the {@code MultiManipulateCommand} that manipulates all tasks in
     * the Storage that contain a given search term.
     */
    @Test
    public void testContainsManipulate() {
        Parser.setTaskList(TASK_LIST);
        for (int i = 1; i <= 3; i++) {
            new TodoCommand("TODO: " + i).execute(TASK_LIST, UI);
            new TodoCommand("to: " + i).execute(TASK_LIST, UI);
        }

        Command findTod = Parser.parse("mark /contains TOD");
        String actualUiFindTod = findTod.execute(TASK_LIST, UI);
        String expectedUiFindTod = "Got it. I've marked these 3 tasks as done:\n"
                + "[T][X] TODO: 1\n"
                + "[T][X] TODO: 2\n"
                + "[T][X] TODO: 3";

        Command findTo = Parser.parse("mark /contains to");
        String actualUiFindTo = findTo.execute(TASK_LIST, UI);
        String expectedUiFindTo = "Got it. I've marked these 3 tasks as done:\n"
                + "[T][X] to: 1\n"
                + "[T][X] to: 2\n"
                + "[T][X] to: 3";

        Command findNonExistent = Parser.parse("delete -c toto");
        String actualUiFindNonExistent = findNonExistent.execute(TASK_LIST, UI);
        String expectedUiFindNonExistent = "No tasks contain that search term. Please try again.";

        for (int i = 0; i < 6; i++) {
            STORAGE.delete(0);
        }

        assertAll(() -> assertEquals(expectedUiFindTod, actualUiFindTod
                ), () -> assertEquals(expectedUiFindTo, actualUiFindTo
                ), () -> assertEquals(expectedUiFindNonExistent, actualUiFindNonExistent)
        );
    }

    /**
     * Tests the {@code MultiManipulateCommand} that manipulates all tasks in
     * the Storage.
     */
    @Test
    public void testAllManipulate() {
        Parser.setTaskList(TASK_LIST);
        for (int i = 1; i <= 3; i++) {
            new TodoCommand("TODO: " + i).execute(TASK_LIST, UI);
        }

        Command allSlash = Parser.parse("mark /all");
        String actualUiAllSlash = allSlash.execute(TASK_LIST, UI);
        String expectedUiAllSlash = "Got it. I've marked these 3 tasks as done:\n"
                + "[T][X] TODO: 1\n"
                + "[T][X] TODO: 2\n"
                + "[T][X] TODO: 3";

        Command singleHyphen = Parser.parse("unmark -all");
        String actualUiSingleHyphen = singleHyphen.execute(TASK_LIST, UI);
        String expectedUiSingleHyphen = "Got it. I've marked these 3 tasks as not completed:\n"
                + "[T][ ] TODO: 1\n"
                + "[T][ ] TODO: 2\n"
                + "[T][ ] TODO: 3";

        Command invalidFlag = Parser.parse("delete all");
        String actualUiInvalidFlag = invalidFlag.execute(TASK_LIST, UI);
        String expectedUiInvalidFlag = "An invalid option was used for the delete command, "
                + "or invalid indexes were provided.\n"
                + "Please try again.";

        Command doubleHyphen = Parser.parse("delete --a");
        String actualUiDoubleHyphen = doubleHyphen.execute(TASK_LIST, UI);
        long ct = STORAGE.size();
        String expectedUiDoubleHyphen = "Got it. I've deleted these 3 tasks:\n"
                + "[T][ ] TODO: 1\n"
                + "[T][ ] TODO: 2\n"
                + "[T][ ] TODO: 3\n"
                + String.format(
                        "Now you have %s task%s in the list.",
                        ct > 0L ? ct : "no",
                        ct != 1L ? "s" : ""
                );

        assertAll(() -> assertEquals(expectedUiAllSlash, actualUiAllSlash
                ), () -> assertEquals(expectedUiSingleHyphen, actualUiSingleHyphen
                ), () -> assertEquals(expectedUiInvalidFlag, actualUiInvalidFlag
                ), () -> assertEquals(expectedUiDoubleHyphen, actualUiDoubleHyphen)
        );
    }
}
