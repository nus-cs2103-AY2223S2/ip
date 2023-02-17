package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTest {
    @DisplayName("Event toString Test")
    @Test
    public void toStringTest() {
        Event event = new Event("test", "2022-01-01", "2023-01-01");
        assertEquals("[E][ ] test (from: Jan 1 2022, to: Jan 1 2023)", event.toString());
    }

    @DisplayName("Event (un)marking Test")
    @Test
    public void markUnmarkTest() {
        Event event = new Event("test", "2022-01-01", "2023-01-01");
        event.markTask();
        assertEquals("[E][X] test (from: Jan 1 2022, to: Jan 1 2023)", event.toString());

        event.unmarkTask();
        assertEquals("[E][ ] test (from: Jan 1 2022, to: Jan 1 2023)", event.toString());
    }

    @DisplayName("Event Export Test")
    @Test
    public void exportDataTest() {
        Event event = new Event("test", "2022-01-01", "2023-01-01");
        assertEquals("Event | marked: 0 ; description: test ; from: 2022-01-01 ; to: 2023-01-01", event.toData());

        event.markTask();
        assertEquals("Event | marked: 1 ; description: test ; from: 2022-01-01 ; to: 2023-01-01", event.toData());
    }
}
