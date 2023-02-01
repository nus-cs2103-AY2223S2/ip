package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parseTodoTest(){
        Parser parser = new Parser();
        try {
            Command actualCommand = parser.parseInput("todo homework");
            assertEquals("addTodo", actualCommand.getType());
            assertEquals("homework", actualCommand.getData()[0]);
        } catch (DukeException e) {
            fail("error: todo parsing is wrong. " + e.getMessage());
        }
    }

    @Test
    public void parseDeadlineTest(){
        Parser parser = new Parser();
        try {
            Command actualCommand = parser.parseInput("deadline return book /by 30/05/2023 6:30");
            assertEquals("addDeadline", actualCommand.getType());
            assertEquals("return book", actualCommand.getData()[0]);
            assertEquals("30/05/2023 6:30", actualCommand.getData()[1]);
        } catch (DukeException e) {
            fail("error: deadline parsing is wrong. " + e.getMessage());
        }
    }

}
