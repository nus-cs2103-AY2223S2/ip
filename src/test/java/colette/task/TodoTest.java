package colette.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStringConversion() {
        Todo testTodo = new Todo("test");
        assertEquals("[T] [ ] test", testTodo.toString());
        testTodo.setDone(true);
        assertEquals("[T] [X] test", testTodo.toString());
    }

    @Test
    public void testFileRepresentationConversion() {
        Todo testTodo = new Todo("test");
        assertEquals("T@0@test", testTodo.getFileRepresentation());
        testTodo.setDone(true);
        assertEquals("T@1@test", testTodo.getFileRepresentation());
    }
}
