package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void test() {
        assertEquals("last command is unable to undo",
                Parser.parseLastCommandType("list"));
    }
}
