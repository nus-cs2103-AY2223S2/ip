package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testTodo() {
        Todo todoTest = new Todo("eat");
        assertEquals("[T][ ] eat", todoTest.toString());
    }
}
