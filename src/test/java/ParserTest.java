import duke.Parser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ParserTest {
    Parser parser = new Parser();
    @Test
    public void testParseByeCommand(){
        Assertions.assertTrue(parser.isByeCommand("bye"));
        Assertions.assertTrue(parser.isByeCommand("bye "));
        Assertions.assertTrue(parser.isByeCommand(" bye"));
    }

    @Test
    public void testParseListCommand(){
        Assertions.assertTrue(parser.isListCommand("list"));
        Assertions.assertTrue(parser.isListCommand("list "));
        Assertions.assertTrue(parser.isListCommand(" list"));
    }
}
