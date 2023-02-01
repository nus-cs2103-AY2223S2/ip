package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void todoText() {
        Todo todo = new Todo("testTodo");
        assertEquals("testTodo", todo.getNameOfTask(), "getNameOfTask() works");
        assertFalse(todo.isDone(), "task done initiated to false");
        todo.taskDone();
        assertTrue(todo.isDone(), "taskDone() works");
        todo.taskNotDone();
        assertFalse(todo.isDone(), "taskNotDone() works");
        assertEquals("T|testTodo|0", todo.toText(), "toText() when not done works");
        todo.taskDone();
        assertEquals("T|testTodo|1", todo.toText(), "toText() when done works");
        todo.taskNotDone();
        assertEquals("[T][ ] testTodo", todo.toString(), "toString() when not done works");
        todo.taskDone();
        assertEquals("[T][X] testTodo", todo.toString(), "toString() when done works");
    }

}