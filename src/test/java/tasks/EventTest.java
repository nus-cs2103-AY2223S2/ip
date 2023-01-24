package tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void addEventTest() {
        Event e = new Event("Larry's birthday", "02/12/2022 1830", "02/12/2022 1930");
        assertEquals("[E][ ] Larry's birthday (from: 02 Dec 2022 6:30 PM to: 02 Dec 2022 7:30 PM)", e.toString());
    }
}