package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

class ParserTest {

    private Parser validParser = new Parser("yyyy-MM-dd", "dd-MMM-yyyy (EEE)");
    private String preFormat = "2022-12-31";
    private String postFormat = "31-Dec-2022 (Sat)";
    private LocalDate validLocalDate = LocalDate.parse(preFormat);

    @Test
    public void parseDate_valid_success() {
        assertEquals(
                validLocalDate,
                validParser.parseDate(preFormat));
    }

    @Test
    public void dateToOutputFormat_valid_sucess() {
        assertEquals(
                postFormat,
                validParser.dateToOutputFormat(validLocalDate));
    }
}