package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringConversion() {
        Todo example = new Todo("description");
        assertEquals("[T][ ] description", example.toString());
    }

    @Test
    public void testDataConversion() {
        Todo example = new Todo("description");
        assertEquals("T/0/description", example.printData());
    }
}