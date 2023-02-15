package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeadlineTest {

    private Deadline deadline;

    @Test
    @DisplayName("The toString method in the Deadline class should return a string of specified format.")
    void testToString() {
        LocalDateTime end = LocalDateTime.of(2022, 05, 05, 22, 00);
        this.deadline = new Deadline("Vet proposal", end, false);
        assertEquals("[D][ ] Vet proposal (by: Thursday, May 05 2022, 22:00)",
                this.deadline.toString(),
                "There is a problem with the toString method in Event class");
    }

}
