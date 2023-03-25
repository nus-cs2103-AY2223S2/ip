package brotherbot.parser;

import brotherbot.exceptions.BroException;

import brotherbot.storage.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parseInvalidInputsTest() throws BroException {
        /**
        // valid inputs test
        dummy.add(new Todo("eat"));
        // AddTaskCommand Testcases
        String validTest1 = "todo eat";
        String validTest2 = "deadline eat /by 11/11/2023 1111";
        String validTest3 = "event eat /from 11/11/2023 1111 /to 11/11/2023 1112";
        String validTest4 = "display";
        String validTest5 = "bye";
        String validTest6 = "mark 1";
        String validTest7 = "delete 1";
        Command x = new AddTaskCommand(validTest1);

        assertEquals(new AddTaskCommand(validTest1), Parser.parse(validTest1,dummy));
        assertEquals(new AddTaskCommand(validTest2), Parser.parse(validTest2,dummy));
        assertEquals(new AddTaskCommand(validTest3), Parser.parse(validTest3,dummy));
        assertEquals(new DisplayCommand(validTest4), Parser.parse(validTest4,dummy));
        assertEquals(new ExitCommand(validTest5), Parser.parse(validTest5, dummy));
        assertEquals(new MarkTaskCommand(validTest6), Parser.parse(validTest6, dummy));
        assertEquals(new DeleteCommand(validTest7), Parser.parse(validTest7, dummy));
        **/
        TaskList dummy = new TaskList();
        Parser parser = new Parser();

        String invalidInput = "deadline eat"; // wrong format
        String typoInput = "tod eat";
        String invalidDeleteInput = "delete 1"; // invalid delete
        String invalidMarkInput = "mark 1"; // invalid mark

        assertThrows(BroException.class, () -> {parser.parse(invalidInput, dummy);});
        assertThrows(BroException.class, () -> {parser.parse(typoInput, dummy);});
        assertThrows(BroException.class, () -> {parser.parse(invalidDeleteInput, dummy);});
        assertThrows(BroException.class, () -> {parser.parse(invalidMarkInput, dummy);});
    }

}