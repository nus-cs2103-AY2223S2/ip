package jeno;

import jeno.parser.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void dateFormatterTest() {
        LocalDateTime expectedDate = LocalDateTime.of(2023, Month.JANUARY, 30, 17, 0);
        String dateTimeToTest = "30/01/2023 17:00";
        Assertions.assertEquals(expectedDate, Parser.dateFormatter(dateTimeToTest));
    }
}
