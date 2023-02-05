package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void printTask_newTask_success() {

        LocalDate startTime = LocalDate.parse("2023-01-01");
        LocalDate endTime = LocalDate.parse("2023-01-02");

        assertEquals("[E][ ] eat (Start: Jan 01 2023 | End: Jan 02 2023)",
                new Event(0, "eat", startTime, endTime).printTask());
    }

}
