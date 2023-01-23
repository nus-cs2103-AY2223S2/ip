package duke.utilities;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void getBy() throws DukeException, ParseException {
        Parser parser = new Parser("deadline return book /by 2/12/2019 1800");
        parser.forDeadline();
        Date actual = parser.getBy();

        Date expected;
        expected = Parser.dateFormat.parse("2/12/2019 1800");

        assertEquals(actual, expected);

    }

    @Test
    void getFrom() throws DukeException, ParseException {
        Parser parser = new Parser("event buy burger /from 2/12/2019 1800 /to 2/12/2019 2100");
        parser.forEvent();
        Date actual = parser.getFrom();

        Date expected = Parser.dateFormat.parse("2/12/2019 1800");

        assertEquals(actual, expected);

    }

    @Test
    void getTo() throws DukeException, ParseException {
        Parser parser = new Parser("event buy burger /from 2/12/2019 1800 /to 2/12/2019 2100");
        parser.forEvent();
        Date actual = parser.getTo();

        Date expected = Parser.dateFormat.parse("2/12/2019 2100");


        assertEquals(actual, expected);

    }
}