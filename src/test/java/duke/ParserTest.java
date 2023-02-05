package duke;

import duke.commands.AddDeadlineCommand;
import duke.commands.Command;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {
    @Test
    public void parseTest() {
        Command c = Parser.parse("deadline grocery /by 19/10/2023");
        LocalDate date = LocalDate.parse("19/10/2023", DateTimeFormatter.ofPattern("d/MM/yyyy"));
        AddDeadlineCommand test = new AddDeadlineCommand("grocery", date);
        assertEquals(c, test);
    }
}
