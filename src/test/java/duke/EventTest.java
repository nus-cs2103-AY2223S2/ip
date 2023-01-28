package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {
    private String description = "drive car";
    private String from = "Monday";
    private String to = "Sunday";
    private Event e = new Event(description, from, to);
    @Test
    public void Test1() {
        assertEquals(e.toString(), "[E][ ] drive car (from: Monday to: Sunday)");
    }
    @Test
    public void Test2() {
        assertEquals(e.toFile(), "E /0 /drive car /from: Monday /to: Sunday");
    }

    @Test
    public void Test3() {
        e.markAsDone();
        assertEquals(e.toString(), "[E][X] drive car (from: Monday to: Sunday)");
    }

    @Test
    public void Test4() {
        e.markAsUndone();
        assertEquals(e.toString(), "[E][ ] drive car (from: Monday to: Sunday)");
    }
}
