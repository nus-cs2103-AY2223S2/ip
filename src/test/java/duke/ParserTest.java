package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void parseTest() {
        Parser parser = new Parser();
        String output = parser.parseInput("deadline hello /by 2019-10-15", new TaskList());
        
        assertEquals(output, " Got it. I've added this task:\n  [D][ ] hello by Oct 15 2019\nNow you have 1 tasks on the list.\n");
    }
}
