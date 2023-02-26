package duke;

import Duke.Exception.InvalidCommandException;
import Duke.Exception.NoDescriptionException;
import org.junit.jupiter.api.Test;
import Duke.Parser;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void Test2(){
        Parser.makeSense("bye");
        assertEquals(false, Parser.parserStatus);
    }

}
