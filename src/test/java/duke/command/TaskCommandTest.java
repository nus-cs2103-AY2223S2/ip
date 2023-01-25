package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskCommandTest extends CommandTest{
    @Test
    public void testValidTodo() {
        TodoCommand todo = new TodoCommand("val");
        long beforeSize = s.size();
        todo.execute(ts, ui);
        s.delete(Integer.parseInt(String.valueOf(s.size() - 1)));
        String expectedUI =
                "      ____________________________________________________________\n" +
                        "Rick: Got it. I've added this task:\n" +
                        "        [T][ ] val\n" +
                        String.format("      Now you have %s tasks in the list.\n", beforeSize + 1) +
                        "      ____________________________________________________________\n\n";
        assertEquals(expectedUI, outContent.toString());
        outContent.reset();
    }

    @Test
    public void testInvalidTodo() {
        Command todo = new TodoCommand();
        todo.execute(ts, ui);
        String expectedUI =
                "      ____________________________________________________________\n" +
                        "Rick: The description of a Todo cannot be empty.\n" +
                        "      ____________________________________________________________\n\n";
        assertEquals(expectedUI, outContent.toString());
        outContent.reset();
    }

    @Test
    public void testValidDeadline() {
        Command deadline = new DeadlineCommand(
                "task one /by 2/2/23 0000"
        );
        long beforeSize = s.size();
        deadline.execute(ts, ui);
        s.delete(Integer.parseInt(String.valueOf(beforeSize)));

        String expectedUI =
                "      ____________________________________________________________\n" +
                        "Rick: Got it. I've added this task:\n" +
                        "        [D][ ] task one (by: Feb 02 2023 12:00AM)\n" +
                        String.format("      Now you have %s tasks in the list.\n", beforeSize + 1) +
                        "      ____________________________________________________________\n\n";
        assertEquals(expectedUI, outContent.toString());

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

        deadlineInvalidUsage.execute(ts, ui);
        String ui1 =
                "      ____________________________________________________________\n" +
                        "Rick: Usage: deadline {task} /by {deadline}\n" +
                        "      ____________________________________________________________\n\n";
        assertEquals(
                ui1,
                outContent.toString()
        );
        outContent.reset();

        deadlineInvalidDate.execute(ts, ui);
        String ui2 =
                "      ____________________________________________________________\n" +
                        "Rick: An invalid date was entered. Please use this format:\n" +
                        "      {day}/{month}/{year} {hour}{minute}\n" +
                        "      Example: 2/2/23 1200\n" +
                        "      ____________________________________________________________\n\n";
        assertEquals(
                ui2,
                outContent.toString()
        );
        outContent.reset();

        deadlineEmpty.execute(ts, ui);
        String ui3 =
                "      ____________________________________________________________\n" +
                        "Rick: The description of a Deadline cannot be empty.\n" +
                        "      ____________________________________________________________\n\n";
        assertEquals(ui3, outContent.toString());
        outContent.reset();
    }

    @Test
    public void testValidEvent() {
        Command event = new EventCommand(
                "task one /from 2/2/23 0000 /to 2/2/23 0001"
        );

        long beforeSize = s.size();
        event.execute(ts, ui);
        s.delete(Integer.parseInt(String.valueOf(beforeSize)));
        String expectedUI =
                "      ____________________________________________________________\n" +
                        "Rick: Got it. I've added this task:\n" +
                        "        [E][ ] task one (from: Feb 2 2023 12:00AM to: Feb 2 2023 12:01AM)\n" +
                        String.format("      Now you have %s tasks in the list.\n", beforeSize + 1) +
                        "      ____________________________________________________________\n\n";
        assertEquals(
                expectedUI,
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

        String ui1 =
                "      ____________________________________________________________\n" +
                        "Rick: Usage: event {task} /from {start} /to {end}\n" +
                        "      ____________________________________________________________\n\n";
        eventWrongFormat.execute(ts, ui);
        assertEquals(
                ui1,
                outContent.toString()
        );
        outContent.reset();

        String ui2 =
                "      ____________________________________________________________\n" +
                        "Rick: An invalid date was entered. Please use this format:\n" +
                        "      {day}/{month}/{year} {hour}{minute}\n" +
                        "      Example: 2/2/23 1200\n" +
                        "      ____________________________________________________________\n\n";
        eventImproperDate.execute(ts, ui);
        assertEquals(
                ui2,
                outContent.toString()
        );
        outContent.reset();

        String ui3 =
                "      ____________________________________________________________\n" +
                        "Rick: The description of a Event cannot be empty.\n" +
                        "      ____________________________________________________________\n\n";

        eventEmpty.execute(ts, ui);
        assertEquals(
                ui3,
                outContent.toString()
        );
        outContent.reset();
    }
}
