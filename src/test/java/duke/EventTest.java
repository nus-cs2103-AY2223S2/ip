package duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        Event e = new Event("go to school", "2019-01-05", "2019-01-08");
        assertEquals(e.toString(), "[E][] go to school (from: Jan 5 2019 to: Jan 8 2019)");
    }
}