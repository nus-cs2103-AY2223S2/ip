package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void addToDoTest() {
        Todo td = new Todo("eat pork");
        assertEquals("[T][ ] eat pork", td.toString());
    }
}