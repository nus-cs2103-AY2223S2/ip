package duke.parser;

import duke.exception.InvalidInputException;
import duke.message.MessageStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void process_byeInput_endStatus() throws InvalidInputException {
        assertEquals(MessageStatus.END, new Parser().process("bye"));
    }

    @Test
    public void process_listInput_listStatus() throws InvalidInputException {
        assertEquals(MessageStatus.LIST, new Parser().process("list"));
    }

    @Test
    public void process_addInput_addStatus() throws InvalidInputException {
        assertEquals(MessageStatus.ADD, new Parser().process("todo content"));
    }
}
