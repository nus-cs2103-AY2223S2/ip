package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseTodoTest(){
        String textInput = "todo read book";
        Parser p = new Parser();
        p.parse(textInput);
        assertEquals(p.action, "todo");
    }

    @Test
    public void parseDeadlineTest(){
        String textInput = "deadline return book /by 2023-01-01";
        Parser p = new Parser();
        p.parse(textInput);
        assertEquals(p.action, "deadline");
    }

    @Test
    public void parseEventTest(){
        String textInput = "event career fair /from 2023-01-01 /to 2023-01-02";
        Parser p = new Parser();
        p.parse(textInput);
        assertEquals(p.action, "event");
    }
}
