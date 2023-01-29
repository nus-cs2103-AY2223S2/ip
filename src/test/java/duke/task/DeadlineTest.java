package duke.task;

import duke.Parser;

import org.junit.jupiter.api.Test;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void toStringTest() {
        Parser p = new Parser(
                null,
                null,
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("dd-MMM-yyyy (EEE)")
        );
        assertEquals(
                "[D][ ] bread By: 01-Jan-2022 (Sat)",
                new Deadline("bread", "2022-01-01", p).toString(p)
        );
    }

    @Test
    public void toLogTest() {
        Parser p = new Parser(
                null,
                null,
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("dd-MMM-yyyy (EEE)")
        );
        assertEquals(
                "[D][ ] bread 2022-01-01",
                new Deadline("bread", "2022-01-01", p).toLog(p)
        );
    }
}
