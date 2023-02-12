package duke.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testFormatting() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        Deadline dl = new Deadline("sleep", LocalDateTime.parse("2023-02-12 1530", formatter));
        assertEquals("sleep (by: 12/02/2023 15:30)", dl.toString());
    }

}
