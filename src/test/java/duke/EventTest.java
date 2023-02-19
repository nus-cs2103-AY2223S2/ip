package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
   public void basicTest() {
        Event event = new Event("12-Jan-2023 1200", "12-Jan-2023 1230", "Guitar Performance");
        String expectedOutput = "[E][ ] Guitar Performance. From: Thursday, 12 January 2023 @ 12:00 hrs." +
              " To: Thursday, 12 January 2023 @ 12:30 hrs";
        assertEquals(event.toString(), expectedOutput);

    }

    @Test
    public void markTest() {
        Event event = new Event("12-Jan-2023 1200", "12-Jan-2023 1230", "Guitar Performance");
        event.mark();
        String expectedOutput = "[E][X] Guitar Performance. From: Thursday, 12 January 2023 @ 12:00 hrs." +
                " To: Thursday, 12 January 2023 @ 12:30 hrs";
        assertEquals(event.toString(), expectedOutput);
    }
}
