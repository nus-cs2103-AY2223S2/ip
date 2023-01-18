package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    private final Task toDo = new ToDo("test");
    private final Task deadline = new Deadline("test", "2020-01-01");
    private final Task event = new Event("test", "2020-01-01", "2020-01-02");

    @Test
    void testToDo() {
        assertFalse(toDo.isDone());
        assertEquals("[T][ ] test", toDo.toString());
    }

    @Test
    void testToDoMark() {
        toDo.mark();
        assertTrue(toDo.isDone());
        assertEquals("[T][X] test", toDo.toString());
    }

    @Test
    void testDeadline() {
        assertFalse(deadline.isDone());
        assertEquals("[D][ ] test (by: Wed, 1 Jan 2020)", deadline.toString());
    }

    @Test
    void testDeadlineMark() {
        deadline.mark();
        assertTrue(deadline.isDone());
        assertEquals("[D][X] test (by: Wed, 1 Jan 2020)", deadline.toString());
    }

    @Test
    void testEvent() {
        assertFalse(event.isDone());
        assertEquals("[E][ ] test (from: Wed, 1 Jan 2020) (to: Thu, 2 Jan 2020)", event.toString());
    }

    @Test
    void testEventMark() {
        event.mark();
        assertTrue(event.isDone());
        assertEquals("[E][X] test (from: Wed, 1 Jan 2020) (to: Thu, 2 Jan 2020)", event.toString());
    }
}