package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void todoText() {
        Todo todo = new Todo("testTodo");
        assertEquals("testTodo", todo.getDescription(), "getNameOfTask() works");
        assertFalse(todo.isDone(), "task done initiated to false");
        todo.markDone();
        assertTrue(todo.isDone(), "taskDone() works");
        todo.markNotDone();
        assertFalse(todo.isDone(), "taskNotDone() works");
        assertEquals("T|testTodo|0", todo.toText(), "toText() when not done works");
        todo.markDone();
        assertEquals("T|testTodo|1", todo.toText(), "toText() when done works");
        todo.markNotDone();
        assertEquals("[T][ ] testTodo", todo.toString(), "toString() when not done works");
        todo.markDone();
        assertEquals("[T][X] testTodo", todo.toString(), "toString() when done works");
    }

}