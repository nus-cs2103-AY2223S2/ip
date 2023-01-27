package task;

import duke.task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void toString_singleInstance_correctRepresentation() {
        String startDateTime = "2024-01-01 12:12";
        String endDateime = "2024-02-02 15:15";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDt = LocalDateTime.parse(startDateTime, formatter);
        LocalDateTime endDt = LocalDateTime.parse(endDateime, formatter);
        Event event = new Event("a", startDt, endDt);
        String expectedMessage = "[E][ ] a (from: 2024-01-01 12:12 to: 2024-02-02 15:15)";
        String actualMessage = event.toString();
        assertEquals(expectedMessage, actualMessage);
    }
}
