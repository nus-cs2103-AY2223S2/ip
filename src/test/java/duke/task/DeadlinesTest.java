package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeadlinesTest {
    private Deadlines deadline;

    @BeforeEach
    public void setUp() {
        deadline = new Deadlines("Testing",
                false,
                LocalDateTime.parse("26/1/2023 2359",
                        DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }

    @Test
    @DisplayName("Ensure Deadline object is created")
    public void testDeadlines() {
        assertNotNull(deadline, "Deadline object should be created");
    }

    @Test
    @DisplayName("Ensure Deadline toString method works correctly")
    public void testToString() {
        assertEquals(deadline.toString(),
                "[D][ ] Testing (by: 26 Jan 2023 2359)");
    }

    @Test
    @DisplayName("Ensure Deadline save method works correctly")
    public void testSave() {
        assertEquals(deadline.save(), "deadline Testing-false-26/1/2023 2359\n");
    }
}
