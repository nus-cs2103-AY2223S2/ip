package duke;

import duke.command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void dummyTest(){
        Command command = Parser.parse("todo buy bread /by 2023-02-01");

        assertEquals("todo buy bread /by 2023-02-01", command.getFullCommand());
    }

    @Test
    public void anotherDummyTest(){
        Command command = Parser.parse("bye");

        assertEquals(true, command.isExit());
    }
}