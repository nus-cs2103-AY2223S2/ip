import task.Deadline;
import task.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class EventTest {
    @Test
    public void testDeadlineString() {
        String eventStartDate = "2024-12-15";
        String eventStartTime = "09:20";
        String eventEndDate = "2024-12-16";
        String eventEndTime = "09:40";

        LocalDate parsedStartDate = LocalDate.parse(eventStartDate);
        LocalDate parsedEndDate = LocalDate.parse(eventEndDate);

        try {
            Date parsedStartTime = new SimpleDateFormat("hh:mm").parse(eventStartTime);
            Date parsedEndTime = new SimpleDateFormat("hh:mm").parse(eventEndTime);
            Event event = new Event("Attend Concert", parsedStartDate, parsedStartTime,
                    parsedEndDate, parsedEndTime, false);
            Assertions.assertEquals("[E][ ] Attend Concert (from: Dec 15 2024 9:20 AM to: Dec 16 2024 9:40 AM)",
                    event.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
