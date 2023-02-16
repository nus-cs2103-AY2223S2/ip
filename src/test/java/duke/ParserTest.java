package duke;

import duke.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {
    @Test
    public void trueCheckEndTest() {
        String command = "bye";
        Parser parser = new Parser();
        assertEquals(true, parser.checkEnd(command));
    }

    @Test
    public void falseCheckEndTest() {
        String command = "hello";
        Parser parser = new Parser();
        assertEquals(false, parser.checkEnd(command));
    }
}
