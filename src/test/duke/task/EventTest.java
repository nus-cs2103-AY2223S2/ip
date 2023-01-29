package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    private Event event = new Event("project meeting", "29/01/2023", "1800", "29/01/2023", "1900");
    @Test
    public void addEvent(){
        assertEquals("[E][ ] project meeting (from: 29 January 2023 18:00 to: 29 January 2023 19:00)", event.toString());
    }

    @Test
    public void markEvent(){
        event.mark();
        assertEquals("[E][X] project meeting (from: 29 January 2023 18:00 to: 29 January 2023 19:00)", event.toString());
    }

    @Test
    public void unmarkEvent(){
        event.unmark();
        assertEquals("[E][ ] project meeting (from: 29 January 2023 18:00 to: 29 January 2023 19:00)", event.toString());
    }

    @Test
    public void saveEvent(){
        assertEquals("event false 29/01/2023 1800 29/01/2023 1900\nproject meeting", event.getDetailsToSave());
    }
}