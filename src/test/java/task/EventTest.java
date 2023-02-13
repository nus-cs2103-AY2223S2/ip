package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class EventTest {
    @Test
    public void formatForWriteToFile_taskIsDone() {
        String expected = "E|X|buy stuff|21/04/1990|29/09/2090";
        Event event = new Event("buy stuff", "21/04/1990", "29/09/2090",
                true);
        assertEquals(event.writeToFile(), expected);
    }
    @Test
    public void formatForWriteToFile_taskIsNotDone() {
        String expected = "E| |buy stuff|21/04/1990|29/09/2090";
        Event event = new Event("buy stuff", "21/04/1990", "29/09/2090",
                false);
        assertEquals(event.writeToFile(), expected);
    }

}