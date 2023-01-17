package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    private final Task toDo = new ToDo("test");
    private final Task deadline = new Deadline("test", "2020-01-01");
    private final Task event = new Event("test", "2020-01-01", "2020-01-02");

    @Test
    void testToDo() {
        assertEquals("test", toDo.desc);
        assertFalse(toDo.isDone);
        assertEquals("[T][ ] test", toDo.toString());
        assertEquals("1. [T][ ] test", toDo.toString(1));
    }

    @Test
    void testToDoMark() {
        toDo.mark();
        assertEquals("test", toDo.desc);
        assertTrue(toDo.isDone);
        assertEquals("[T][X] test", toDo.toString());
        assertEquals("1. [T][X] test", toDo.toString(1));
    }

    @Test
    void testDeadline() {
        assertEquals("test", deadline.desc);
        assertFalse(deadline.isDone);
        assertEquals("[D][ ] test (by: Wed, 1 Jan 2020)", deadline.toString());
        assertEquals("1. [D][ ] test (by: Wed, 1 Jan 2020)", deadline.toString(1));
    }

    @Test
    void testDeadlineMark() {
        deadline.mark();
        assertEquals("test", deadline.desc);
        assertTrue(deadline.isDone);
        assertEquals("[D][X] test (by: Wed, 1 Jan 2020)", deadline.toString());
        assertEquals("1. [D][X] test (by: Wed, 1 Jan 2020)", deadline.toString(1));
    }

    @Test
    void testEvent() {
        assertEquals("test", event.desc);
        assertFalse(event.isDone);
        assertEquals("[E][ ] test (from: Wed, 1 Jan 2020) (to: Thu, 2 Jan 2020)", event.toString());
        assertEquals("1. [E][ ] test (from: Wed, 1 Jan 2020) (to: Thu, 2 Jan 2020)", event.toString(1));
    }

    @Test
    void testEventMark() {
        event.mark();
        assertEquals("test", event.desc);
        assertTrue(event.isDone);
        assertEquals("[E][X] test (from: Wed, 1 Jan 2020) (to: Thu, 2 Jan 2020)", event.toString());
        assertEquals("1. [E][X] test (from: Wed, 1 Jan 2020) (to: Thu, 2 Jan 2020)", event.toString(1));
    }
}