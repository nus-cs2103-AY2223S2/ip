package duke;

import Duke.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests functions in Parser.
 */
public class ParserTest {
    @Test
    public void Test2(){
        Parser.makeSense("bye");
        assertEquals(false, Parser.parserStatus);
    }

}
