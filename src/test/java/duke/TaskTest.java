package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testTodo(){
        Todo todoTest = new Todo("eat");
        assertEquals("[T][ ] eat", todoTest.toString());
    }
}