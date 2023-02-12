package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    public void parseTask_wrongType_exceptionThrown() {
        try {
            Command command = Parser.parseUserResponse(new Ui(), null, null, "delete string");
            command.execute();
        } catch (DukeException e) {
            assertEquals("Given task number is invalid!", e.getMessage());
        }
    }

    @Test
    public void parseUserResponse_unknownCommand_exceptionThrown() {
        try {
            Parser.parseUserResponse(new Ui(), null, null, "eat");
        } catch (DukeException e) {
            assertEquals("Invalid command entered!", e.getMessage());
        }
    }
}
