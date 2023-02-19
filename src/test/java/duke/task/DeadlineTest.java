package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime byDateTime = LocalDateTime.parse("01-01-2023 1800", formatter);

        assertEquals("[D][ ] d1 (by: Jan 01 2023 06:00 PM)",
                new Deadline("d1", byDateTime).toString());
    }

    @Test
    public void getFileRepresentation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime byDateTime = LocalDateTime.parse("01-01-2023 1800", formatter);

        assertEquals("D~ ~d1~2023-01-01T18:00",
                new Deadline("d1", byDateTime).getFileRepresentation());
    }

    @Test
    public void mark() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime byDateTime = LocalDateTime.parse("01-01-2023 1800", formatter);
        Deadline deadline = new Deadline("d1", byDateTime);

        deadline.mark();

        assertEquals("[D][X] d1 (by: Jan 01 2023 06:00 PM)",
                deadline.toString());
    }

}
