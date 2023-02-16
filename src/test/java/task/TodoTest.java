package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    void testInitiateEvent() {
        Todo newTodo = new Todo("testTodo");
        assertEquals("testTodo", newTodo.getDescription(), "getNameOfTask() works");
        assertFalse(newTodo.isDone(), "task done initiated to false");
    }

    @Test
    void testMarkTaskDone() {
        Todo newTodo = new Todo("testTodo");
        newTodo.markDone();
        assertTrue(newTodo.isDone(), "taskDone() works");
        assertEquals("T|1| |testTodo", newTodo.toText(), "toText() when done");
        assertEquals("[T][X][ ] testTodo", newTodo.toString(), "toString() when done");
    }

    @Test
    void testMarkTaskNotDone() {
        Todo newTodo = new Todo("testTodo");
        newTodo.markNotDone();
        assertFalse(newTodo.isDone(), "taskNotDone() works");
        assertEquals("T|0| |testTodo", newTodo.toText(), "toText() when not done");
        assertEquals("[T][ ][ ] testTodo", newTodo.toString(), "toString() when not done");
    }

    @Test
    void testRecurrence() {
        Todo newTodo = new Todo("testTodo");
        newTodo.setRecurrence("monthly");
        assertEquals(newTodo.getRecurrence(), "M", "set recurrence of task to monthly");
        assertEquals("T|0|M|testTodo", newTodo.toText(), "toText() when not done");
        assertEquals("[T][ ][M] testTodo", newTodo.toString(), "toString() when not done");
    }
}

