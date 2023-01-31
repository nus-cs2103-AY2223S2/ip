package jeo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testToDo_addedString() {
        ToDo testTask = new ToDo("read book");
        assertEquals("[T][ ] read book", testTask.toString());
    }

    @Test
    public void testToDo_marked() {
        ToDo testTask = new ToDo("read book");
        testTask.markAsDone();
        assertEquals("[T][X] read book", testTask.toString());
    }

}
