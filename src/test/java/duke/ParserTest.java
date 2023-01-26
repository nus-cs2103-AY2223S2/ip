package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class ParserTest {
    @Test
    public void parseTask_wrongType_exceptionThrown() {
        try {
            String[] input = {"delete", "string"};
            Parser.parseTask(input);
        } catch(DukeException e) {
            assertEquals("Given task number is invalid!", e.getMessage());
        }
    }

    @Test
    public void parseUserResponse_unknownCommand_exceptionThrown() {
        try {
            Parser.parseUserResponse("eat");
        } catch(DukeException e) {
            assertEquals("Invalid command entered!", e.getMessage());
        }
    }
}
