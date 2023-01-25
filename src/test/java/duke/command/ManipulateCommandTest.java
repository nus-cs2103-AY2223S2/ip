package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManipulateCommandTest extends CommandTest {
    @Test
    public void testMark() {
        Command todo = new TodoCommand("task one");
        long size = s.size();
        todo.execute(ts, ui);
        outContent.reset();

        Command mark = new MarkCommand(Integer.parseInt(String.valueOf(size)));
        mark.execute(ts, ui);
        s.delete(Integer.parseInt(String.valueOf(size)));
        String expectedUI =
                "      ____________________________________________________________\n" +
                "Rick: Nice! I've marked this task as done:\n" +
                "        [T][X] task one\n" +
                "      ____________________________________________________________\n\n";
        String actual = outContent.toString();
        outContent.reset();
        assertEquals(expectedUI, actual);
    }

    @Test
    public void testUnmark() {
        Command todo = new TodoCommand("task one");
        int size = Integer.parseInt(String.valueOf(s.size()));
        todo.execute(ts, ui);
        outContent.reset();

        Command unmark = new UnmarkCommand(size);
        unmark.execute(ts, ui);
        s.delete(size);
        String expectedUI =
                "      ____________________________________________________________\n" +
                "Rick: OK, I've marked this task as not done yet:\n" +
                "        [T][ ] task one\n" +
                "      ____________________________________________________________\n\n";
        String actual = outContent.toString();
        outContent.reset();
        assertEquals(expectedUI, actual);
    }

    @Test
    public void testDelete() {
        Command todo = new TodoCommand("task one");
        int size = Integer.parseInt(String.valueOf(s.size()));
        todo.execute(ts, ui);
        outContent.reset();

        Command delete = new DeleteCommand(size + 1);
        delete.execute(ts, ui);
        String expectedUI =
                "      ____________________________________________________________\n" +
                "Rick: Noted. I've removed this task:\n" +
                "        [T][ ] task one\n" +
                String.format("      Now you have %s tasks in the list.\n", size) +
                "      ____________________________________________________________\n\n";
        String actual = outContent.toString();
        outContent.reset();
        assertEquals(expectedUI, actual);
    }

    @Test
    public void testInvalidIndex() {
        int beforeSize = Integer.parseInt(String.valueOf(s.size()));
        Command todo = new TodoCommand("task one");
        todo.execute(ts, ui);
        outContent.reset();

        Command invalidIndex = new MarkCommand(beforeSize + 2);
        s.delete(beforeSize);
        invalidIndex.execute(ts, ui);
        String expectedUI =
                "      ____________________________________________________________\n" +
                "Rick: An invalid index was entered. Please try again.\n" +
                "      ____________________________________________________________\n\n";
        String actual = outContent.toString();
        outContent.reset();
        assertEquals(expectedUI, actual);
    }
}
