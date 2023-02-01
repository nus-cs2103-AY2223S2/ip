package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testTo() {
        Event event = Event.to("competition /from 22/10/2022 0800 /to 22/10/2022 1700");
        String expected = "[E][ ] competition (from: Oct 22 2022 0800 to: Oct 22 2022 1700)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testMark() {
        Event event = Event.to("competition /from 22/10/2022 0800 /to 22/10/2022 1700");
        event.mark();
        String expected = "[E][X] competition (from: Oct 22 2022 0800 to: Oct 22 2022 1700)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testUnMark() {
        Event event = Event.to("competition /from 22/10/2022 0800 /to 22/10/2022 1700");
        event.mark();
        String expected1 = "[E][X] competition (from: Oct 22 2022 0800 to: Oct 22 2022 1700)";
        String expected2 = "[E][ ] competition (from: Oct 22 2022 0800 to: Oct 22 2022 1700)";
        assertEquals(expected1, event.toString());

        event.unMark();
        assertEquals(expected2, event.toString());
    }

    @Test
    public void testGetStatusIcon() {
        Event event = Event.to("competition /from 22/10/2022 0800 /to 22/10/2022 1700");
        assertEquals(event.getStatusIcon(), " ");

        event.mark();
        assertEquals(event.getStatusIcon(), "X");
    }

    @Test
    public void testTaskToSavedForm() {
        String expected = "competition /from 22/10/2022 0800 /to 22/10/2022 1700";
        Event event = Event.to(expected);
        String expected1 = "event " + expected + "0";
        assertEquals(expected1, event.taskToSavedForm());

        event.mark();
        String expected2 = "event " + expected + "1";
        assertEquals(expected2, event.taskToSavedForm());
    }
}
