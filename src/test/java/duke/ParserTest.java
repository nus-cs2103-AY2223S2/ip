package duke;

import duke.command.AddCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParserTest {

    Ui ui = new Ui();
    @Test
    public void isValidCommand_validCommand_true() {
        String input = "mark";
        Assertions.assertTrue(Parser.isValidCommand(input));
    }

    @Test
    public void isValidCommand_inValidCommand_false() {
        String input = "lits";
        Assertions.assertFalse(Parser.isValidCommand(input));
    }

    @Test
    public void parse_correctParseTime() throws DukeException{
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("ddMMuuuu HHmm");
        String expected = "2022-02-02T19:00";
        LocalDateTime by = LocalDateTime.parse("02022022 1900", dateformatter);
        Assertions.assertEquals(by.toString(), expected);
    }
}
