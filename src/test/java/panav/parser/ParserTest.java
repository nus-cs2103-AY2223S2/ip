package panav.parser;
import org.junit.jupiter.api.Test;
import panav.command.Command;
import panav.command.DeadlineCommand;
import panav.task.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {

    @Test
    public void parse_deadlineCommand_failure() {

        assertEquals(new DeadlineCommand("work", "tomorrow"), Parser.parse("work /by tomorrow"));
    }
}
