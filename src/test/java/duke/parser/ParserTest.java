package duke.parser;

import duke.tasklist.TaskListStub;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseRawCommand_validCommands_success() {
        Parser ps = new Parser(new Ui(), new TaskListStub());

        assertEquals(ps.parseRawCommand("bye"), CommandType.BYE);
        assertEquals(ps.parseRawCommand("list"), CommandType.LIST);
        assertEquals(ps.parseRawCommand(("help")), CommandType.HELP);
        assertEquals(ps.parseRawCommand("mark 1"), CommandType.MARK);
        assertEquals(ps.parseRawCommand("unmark 2"), CommandType.UNMARK);
        assertEquals(ps.parseRawCommand("delete 1"), CommandType.DELETE);
        assertEquals(ps.parseRawCommand("todo Task1"), CommandType.TODO);
        assertEquals(ps.parseRawCommand("deadline Task2 /by 2023-02-02"), CommandType.DEADLINE);
        assertEquals(ps.parseRawCommand("event Task3 /from 2023-02-02 12:00 /to 2023-02-02 18:00"), CommandType.EVENT);
        assertEquals(ps.parseRawCommand("on 2023-02-02"), CommandType.ON);
        assertEquals(ps.parseRawCommand("bye hello"), CommandType.INVALID);
        assertEquals(ps.parseRawCommand("bye  "), CommandType.INVALID);
        assertEquals(ps.parseRawCommand("hi hi"), CommandType.INVALID);

    }

    @Test
    public void parseRawCommand_invalidCommands_outputNothing() {
        Parser ps = new Parser(new Ui(), new TaskListStub());

        assertEquals(ps.parseRawCommand("mark -1"), CommandType.NOTHING);
        assertEquals(ps.parseRawCommand("unmark 4"), CommandType.NOTHING);
        assertEquals(ps.parseRawCommand("delete 0"), CommandType.NOTHING);
        assertEquals(ps.parseRawCommand("deadline /by 2023-02-02"), CommandType.NOTHING);
        assertEquals(ps.parseRawCommand("deadline TaskName /by"), CommandType.NOTHING);
        assertEquals(ps.parseRawCommand("deadline TaskName by 2023-02-02"), CommandType.NOTHING);
        assertEquals(ps.parseRawCommand("event TaskName /from 2023-02-02 /to 2023-01-02"), CommandType.NOTHING);
        assertEquals(ps.parseRawCommand("on 5 Feb"), CommandType.NOTHING);
    }

}
