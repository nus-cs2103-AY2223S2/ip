package duke;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void deadlineCommand_deadlineTask_success() {
        try {
            String[] parsed = Parser.parseCommand("deadline read book /by 22-01-2023 16:00");
            assertArrayEquals(new String[]{"deadline", "read book", "22-01-2023 16:00"}, parsed);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deadlineFileCommand_deadlineTask_success() {
        try {
            String[] parsed = Parser.parseFileCommand("1.[D][ ] read book /by Jan 22 2023, 16:00");
            assertArrayEquals(new String[]{"D", " ", "read book", "Jan 22 2023, 16:00"}, parsed);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void eventCommand_eventTask_success() {
        try {
            String[] parsed = Parser.parseCommand("event swim /from 24-01-2023 09:15 /to 24-01-2023 10:30");
            assertArrayEquals(new String[]{"event", "swim", "24-01-2023 09:15", "24-01-2023 10:30"}, parsed);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
