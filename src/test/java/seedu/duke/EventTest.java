package seedu.duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void printTask_newTask_success() {

        LocalDate startTime = LocalDate.parse("2023-01-01");
        LocalDate endTime = LocalDate.parse("2023-01-02");

        assertEquals("[E][ ] eat (Start: Jan 01 2023 | End: Jan 02 2023)",
                new Event(0, "eat", startTime, endTime).printTask());
    }

}
