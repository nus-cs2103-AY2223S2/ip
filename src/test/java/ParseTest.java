//package seedu;

import parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParseTest {
    @Test
    public void parserTodoCommandTest(){
        Parser parser= new Parser();
        assertEquals(parser.parse("todo homework"), "TODO");
    }

    @Test
    public void parserListCommandTest(){
        Parser parser= new Parser();
        assertEquals(parser.parse("list"), "LIST");
    }
}