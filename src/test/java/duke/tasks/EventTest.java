package duke.tasks;

import duke.utils.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    private Event event;

    @Test
    @DisplayName("The toString method in the Event class should return a string of specified format.")
    void testToString() {
        LocalDateTime start = LocalDateTime.of(2022, 05, 05, 19, 00);
        LocalDateTime end = LocalDateTime.of(2022, 05, 05, 22, 00);
        this.event = new Event("Access meeting", start, end, false);
        assertEquals("[E][ ] Access meeting (from: Thursday, May 05 2022, 19:00, "
                + "to: Thursday, May 05 2022, 22:00)",
                this.event.toString(),
                "There is a problem with the toString method in Event class");
    }
}
