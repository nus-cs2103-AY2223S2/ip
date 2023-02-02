import duke.data.TypeOfTask;
import duke.parser.Parser;
import duke.exception.DukeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
* ParserTest will test the functionality of parser
 */
public class ParserTest {
    @Test
    void testDeleteInputException() throws DukeException {
        Parser parser = new Parser();
        String[] fakeInput = new String[]{"Delete","2","3"};
        Assertions.assertThrows(DukeException.class, () -> parser.convertToUserInput(fakeInput,TypeOfTask.delete,""));
    }
    @Test
    void testConvertToLocalDateException() throws DukeException {
        Parser parser = new Parser();
        //String[] fakeInput = new String[]{"20/20/20"};
        String fakeInput = "20/20/20";
        Assertions.assertThrows(DukeException.class,() -> parser.covertToLocalDate(fakeInput));
    }

    @Test
    void testConvertToLocalTimeException() throws DukeException {
        Parser parser = new Parser();
        String fakeInput = "2PM";
        Assertions.assertThrows(DukeException.class, () -> parser.convertToLocalTime(fakeInput));
    }
}
