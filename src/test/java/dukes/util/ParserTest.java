package dukes.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void validateTime() {
        Parser parser = new Parser();
        String line = "24/01/2023";
        assertEquals(LocalDate.parse("2023-01-24"),
                parser.validateTime(line));
    }
}