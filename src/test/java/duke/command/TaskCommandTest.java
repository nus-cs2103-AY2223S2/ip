package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskCommandTest extends CommandTest{
    @Test
    public void testValidTodo() {
        TodoCommand todo = new TodoCommand("val");
        long beforeSize = storage.size();
        todo.execute(taskList, ui);
        storage.delete(Integer.parseInt(String.valueOf(storage.size() - 1)));
        String expectedUi =
                "      ____________________________________________________________\n" +
                        "Rick: Got it. I've added this task:\n" +
                        "        [T][ ] val\n" +
                        String.format("      Now you have %s tasks in the list.\n", beforeSize + 1) +
                        "      ____________________________________________________________\n\n";
        assertEquals(expectedUi, outContent.toString());
        outContent.reset();
    }

    @Test
    public void testInvalidTodo() {
        Command todo = new TodoCommand();
        todo.execute(taskList, ui);
        String expectedUi =
                "      ____________________________________________________________\n" +
                        "Rick: The description of a Todo cannot be empty.\n" +
                        "      ____________________________________________________________\n\n";
        assertEquals(expectedUi, outContent.toString());
        outContent.reset();
    }

    @Test
    public void testValidDeadline() {
        Command deadline = new DeadlineCommand(
                "task one /by 2/2/23 0000"
        );
        long beforeSize = storage.size();
        deadline.execute(taskList, ui);
        storage.delete(Integer.parseInt(String.valueOf(beforeSize)));

        String expectedUi =
                "      ____________________________________________________________\n" +
                        "Rick: Got it. I've added this task:\n" +
                        "        [D][ ] task one (by: Feb 02 2023 12:00AM)\n" +
                        String.format("      Now you have %s tasks in the list.\n", beforeSize + 1) +
                        "      ____________________________________________________________\n\n";
        assertEquals(expectedUi, outContent.toString());

        outContent.reset();
    }

    @Test
    public void testInvalidDeadline() {
        Command deadlineInvalidUsage = new DeadlineCommand(
                "task one"
        );
        Command deadlineInvalidDate = new DeadlineCommand(
                "task one /by 2-2-23 12am"
        );

        Command deadlineEmpty = new DeadlineCommand();

        deadlineInvalidUsage.execute(taskList, ui);
        String expectedUiOne =
                "      ____________________________________________________________\n" +
                        "Rick: Usage: deadline {task} /by {deadline}\n" +
                        "      ____________________________________________________________\n\n";
        assertEquals(
                expectedUiOne,
                outContent.toString()
        );
        outContent.reset();

        deadlineInvalidDate.execute(taskList, ui);
        String expectedUiTwo =
                "      ____________________________________________________________\n" +
                        "Rick: An invalid date was entered. Please use this format:\n" +
                        "      {day}/{month}/{year} {hour}{minute}\n" +
                        "      Example: 2/2/23 1200\n" +
                        "      ____________________________________________________________\n\n";
        assertEquals(
                expectedUiTwo,
                outContent.toString()
        );
        outContent.reset();

        deadlineEmpty.execute(taskList, ui);
        String expectedUiThree =
                "      ____________________________________________________________\n" +
                        "Rick: The description of a Deadline cannot be empty.\n" +
                        "      ____________________________________________________________\n\n";
        assertEquals(expectedUiThree, outContent.toString());
        outContent.reset();
    }

    @Test
    public void testValidEvent() {
        Command event = new EventCommand(
                "task one /from 2/2/23 0000 /to 2/2/23 0001"
        );

        long beforeSize = storage.size();
        event.execute(taskList, ui);
        storage.delete(Integer.parseInt(String.valueOf(beforeSize)));
        String expectedUi =
                "      ____________________________________________________________\n" +
                        "Rick: Got it. I've added this task:\n" +
                        "        [E][ ] task one (from: Feb 2 2023 12:00AM to: Feb 2 2023 12:01AM)\n" +
                        String.format("      Now you have %s tasks in the list.\n", beforeSize + 1) +
                        "      ____________________________________________________________\n\n";
        assertEquals(
                expectedUi,
                outContent.toString()
        );
        outContent.reset();
    }

    @Test
    public void testInvalidEvent() {
        Command eventWrongFormat = new EventCommand(
                "task one"
        );

        Command eventImproperDate = new EventCommand(
                "task one /from 2-2-23 12am /to 2-2-23 1am"
        );

        Command eventEmpty = new EventCommand();

        String expectedUiOne =
                "      ____________________________________________________________\n" +
                        "Rick: Usage: event {task} /from {start} /to {end}\n" +
                        "      ____________________________________________________________\n\n";
        eventWrongFormat.execute(taskList, ui);
        assertEquals(
                expectedUiOne,
                outContent.toString()
        );
        outContent.reset();

        String expectedUiTwo =
                "      ____________________________________________________________\n" +
                        "Rick: An invalid date was entered. Please use this format:\n" +
                        "      {day}/{month}/{year} {hour}{minute}\n" +
                        "      Example: 2/2/23 1200\n" +
                        "      ____________________________________________________________\n\n";
        eventImproperDate.execute(taskList, ui);
        assertEquals(
                expectedUiTwo,
                outContent.toString()
        );
        outContent.reset();

        String expectedUiThree =
                "      ____________________________________________________________\n" +
                        "Rick: The description of a Event cannot be empty.\n" +
                        "      ____________________________________________________________\n\n";

        eventEmpty.execute(taskList, ui);
        assertEquals(
                expectedUiThree,
                outContent.toString()
        );
        outContent.reset();
    }
}
