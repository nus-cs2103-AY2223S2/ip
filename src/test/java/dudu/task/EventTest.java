package dudu.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dudu.exception.InvalidCommandException;

public class EventTest {
    private static final String name = "event task";
    private static final String from = "2022-10-12";
    private static final String to = "2023-01-12";
    private Event task;
    public EventTest() throws InvalidCommandException {
        this.task = new Event(name, from, to);
    }

    @Test
    void getStatusTest() {
        assertEquals("0", task.getStatus());
    }
    @Test
    void toStringTest() {
        assertEquals(
                String.format("[E][%s] %s (from: Oct 12 2022 to: Jan 12 2023)", task.isDone() ? "X" : " ", name),
                task.toString()
        );
    }
}
