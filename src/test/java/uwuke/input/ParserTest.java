package uwuke.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import uwuke.controller.Parser;

public class ParserTest {
    @Test
    public void parseDeadline_normalInput_success() throws Exception {
        String[] actual = Parser.parseDeadline("deadline complete A-JUnit /by today");
        String[] expected = {"complete A-JUnit", "today"};
        assertEquals(true, Arrays.equals(actual, expected));
    }

    @Test
    public void parseDeadline_emptyString_throwsException() {
        assertThrows(Exception.class, () -> Parser.parseDeadline(""));
    }

    @Test
    public void parseDeadline_null_throwsException() {
        assertThrows(Exception.class, () -> Parser.parseDeadline(null));
    }
}
