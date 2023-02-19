package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class EventTest {
    @Test
    public void formatForWriteToFile_taskIsDone() {
        String expected = "E|l|X|buy stuff|21/04/1990|29/09/2090";
        Event event = new Event("buy stuff", "21/04/1990", "29/09/2090",
                true, PriorityLevel.LOW);
        assertEquals(event.writeToFile(), expected);
    }
    @Test
    public void formatForWriteToFile_taskIsNotDone() {
        String expected = "E|l| |buy stuff|21/04/1990|29/09/2090";
        Event event = new Event("buy stuff", "21/04/1990", "29/09/2090",
                false, PriorityLevel.LOW);
        assertEquals(event.writeToFile(), expected);
    }
}
