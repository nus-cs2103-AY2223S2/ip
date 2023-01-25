package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class ParserTest {

    @Test
    void testGetDate() {
        String[] testString = { "event", "test", "/by", "2019-10-15" };

        LocalDate localDate = Parser.getDate(testString);

        assertEquals(localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")), "Oct 15 2019");
    }
}