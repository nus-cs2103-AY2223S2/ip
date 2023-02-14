import task.Deadline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DeadlineTest {
    @Test
    public void testDeadlineString() {
        String deadlineDate = "2024-12-15";
        LocalDate parsedDate = LocalDate.parse(deadlineDate);
        String deadlineTime = "09:20";
        try {
            Date parsedTime = new SimpleDateFormat("hh:mm").parse(deadlineTime);
            Deadline deadline = new Deadline("Attend Concert", parsedDate, parsedTime, false);
            Assertions.assertEquals("[D][ ] Attend Concert (by: Dec 15 2024 9:20 AM)" ,deadline.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
