package alfred.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import alfred.exception.AlfredException;
public class ParserTest {
    @Test
    public void getCommandTest() {
        Parser parser = new Parser();
        String userInput = "mark 3";
        Parser.Action action = parser.getCommand(userInput);
        assertEquals(Parser.Action.MARK, action);
    }
    @Test
    public void getEventDateDetailsTest() throws AlfredException {
        Parser parser = new Parser();
        LocalDate startDate = LocalDate.of(2021, 10, 10);
        LocalDate endDate = LocalDate.of(2021, 11, 15);
        String userInput = "event overseas /from 2021-10-10 /to 2021-11-15";
        LocalDate[] dates = parser.getEventDateDetails(userInput);
        LocalDate[] checkDates = new LocalDate[]{startDate, endDate};
        assertEquals(checkDates[0], dates[0]);
        assertEquals(checkDates[1], dates[1]);
    }
}
