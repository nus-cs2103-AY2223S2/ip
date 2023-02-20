package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    private String description = "drive car";
    private String from = "Monday";
    private String to = "Sunday";
    private Event e = new Event(description, from, to);
    @Test
    public void inputParsingTest() {
        assertEquals(e.toString(), "[E][ ] drive car (from: Monday to: Sunday)");
    }
    @Test
    public void fileParsingTest() {
        assertEquals(e.toFile(), "E /0 /drive car /from: Monday /to: Sunday");
    }

    @Test
    public void markAsDoneTest() {
        e.markAsDone();
        assertEquals(e.toString(), "[E][X] drive car (from: Monday to: Sunday)");
    }

    @Test
    public void markAsUndoneTest() {
        e.markAsUndone();
        assertEquals(e.toString(), "[E][ ] drive car (from: Monday to: Sunday)");
    }
}
