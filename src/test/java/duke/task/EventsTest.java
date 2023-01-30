package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventsTest {
    private Events event;

    @BeforeEach
    public void setUp() {
        event = new Events("Testing",
                false,
                LocalDateTime.parse("26/01/2023 0000",
                        DateTimeFormatter.ofPattern("d/M/yyyy HHmm")),
                LocalDateTime.parse("26/01/2023 2359",
                        DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }

    @Test
    @DisplayName("Ensure Event object is created")
    public void testEvents() {
        assertNotNull(event, "Deadline object should be created");
    }

    @Test
    @DisplayName("Ensure Event toString method works correctly")
    public void testToString() {
        assertEquals(event.toString(),
                "[E][ ] Testing (from: 26 Jan 2023 0000 to: 26 Jan 2023 2359)");
    }

    @Test
    @DisplayName("Ensure Event save method works correctly")
    public void testSave() {
        assertEquals(event.save(), "event Testing-false-26/1/2023 0000-26/1/2023 2359\n");
    }
}
