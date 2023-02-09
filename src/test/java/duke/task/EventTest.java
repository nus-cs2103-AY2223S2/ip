package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {
    @Test
    public void test1() {
        Event generate = new Event("project meeting", "01/01/2023 1300",  "01/01/2023 1400");
        String correct = "[E]" + "[ ] " + "project meeting" + " (from: " + "1 January 2023, 1300H" + " to: " + "1 January 2023, 1400H" + ")";
        assertEquals(correct, generate.toString());
    }
}
