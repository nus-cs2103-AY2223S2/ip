package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.task.Event;

public class EventTest {

    @Test
    public void toString_singleInstance_correctRepresentation() {
        String startDateTime = "2024-01-01 01:01";
        String endDatetime = "2024-02-02 02:02";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDt = LocalDateTime.parse(startDateTime, formatter);
        LocalDateTime endDt = LocalDateTime.parse(endDatetime, formatter);
        String descr = "Event test";
        Event event = new Event(descr, startDt, endDt);
        String expectedMessage = "Event test (from: 2024-01-01 01:01 to: 2024-02-02 02:02)";
        String actualMessage = event.toString();
        assertEquals(expectedMessage, actualMessage);
    }
}
