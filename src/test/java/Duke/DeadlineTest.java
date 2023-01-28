package Duke;

import Duke.Tasks.Deadline;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    @Test
    public void changeFormatTest(){
        Deadline dl = new Deadline("Homework", LocalDateTime.parse("2021-08-22 1800", format));
        assertEquals(dl.changeFormat(), "D / 0 / Homework / 2021-08-22 1800");

    }

    @Test
    public void toStringTest(){
        Deadline dl = new Deadline("Homework", LocalDateTime.parse("2021-08-22 1800", format));
        assertEquals(dl.toString(), "    [D][ ] Homework (by: Aug 22 2021 0600 PM)");

    }



}


