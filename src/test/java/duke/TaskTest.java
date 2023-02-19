package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    void testToDoMark() {
        Todo todo = new Todo("test");
        todo.mark();
        assertEquals(todo.toString(), "[T][X] test");

    }

    @Test
    void testEvent() {
        Event event = new Event("borrow book");
        assertEquals(event.toString(), "[E][ ] borrow book");
    }
}