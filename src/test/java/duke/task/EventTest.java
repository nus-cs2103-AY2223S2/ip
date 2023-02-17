package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exceptions.InvalidDateException;

public class EventTest {
    @Test
    public void addEvent() {
        Event event = null;
        try {
            event = new Event("Climbing Camp", "2023-01-31", "2023-02-02");
        } catch (InvalidDateException e) {
            System.out.println(e);
        }
        assertEquals("[E][] Climbing Camp | FROM: Jan 31 2023 TO: Feb 2 2023", event.getStatusIcon());
    }

    @Test
    public void mark() {
        Event event = null;
        try {
            event = new Event("Climbing Camp", "2023-01-31", "2023-02-02");
        } catch (InvalidDateException e) {
            System.out.println(e);
        }
        event.markTask();
        assertEquals("[E][X] Climbing Camp | FROM: Jan 31 2023 TO: Feb 2 2023", event.getStatusIcon());
    }

    @Test
    public void unmark() {
        Event event = null;
        try {
            event = new Event("Climbing Camp", "2023-01-31", "2023-02-02");
        } catch (InvalidDateException e) {
            System.out.println(e);
        }
        event.markTask();
        event.unmarkTask();
        assertEquals("[E][] Climbing Camp | FROM: Jan 31 2023 TO: Feb 2 2023", event.getStatusIcon());
    }

    @Test
    public void encode() {
        Event event = null;
        try {
            event = new Event("Climbing Camp", "2023-01-31", "2023-02-02");
        } catch (InvalidDateException e) {
            System.out.println(e);
        }
        event.markTask();
        assertEquals("event ### true ### Climbing Camp ### 2023-01-31 ### 2023-02-02", event.encode());
    }
}
