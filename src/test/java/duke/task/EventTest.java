package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    void toString_normalInput_success() {
        LocalDateTime startDateTime = LocalDateTime.of(2023, 1, 30, 17, 30);
        LocalDateTime endDateTime = LocalDateTime.of(2023, 1, 30, 18, 30);
        Event event = new Event("CS2103T Meeting", startDateTime, endDateTime);

        String expectedResult = "[E][ ] CS2103T Meeting (from: Mon, 30 Jan 2023, 05:30 PM "
                + "to: Mon, 30 Jan 2023, 06:30 PM)";
        String actualResult = event.toString();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getTaskState_normalInput_success() {
        LocalDateTime startDateTime = LocalDateTime.of(2023, 1, 30, 17, 30);
        LocalDateTime endDateTime = LocalDateTime.of(2023, 1, 30, 18, 30);
        Event event = new Event("CS2103T Meeting", startDateTime, endDateTime);

        String expectedResult = "E | 0 | CS2103T Meeting | 2023-01-30T17:30 | 2023-01-30T18:30";
        String actualResult = event.getTaskState();

        assertEquals(expectedResult, actualResult);
    }
}
