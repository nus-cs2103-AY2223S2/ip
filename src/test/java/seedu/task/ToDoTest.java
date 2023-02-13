package seedu.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.ToDo;

/**
 * Test for ToDo tasks.
 */
public class ToDoTest {
    /**
     * Tests if the ToString method of the ToDo task is correct.
     */
    @Test
    public void testToDoDescription() {
        ToDo toDo = new ToDo("return book");
        assertEquals("[T][ ] return book", toDo.toString());
    }

    /**
     * Tests if ToDo task is correctly unmarked.
     */
    @Test
    public void testUnmark() {
        ToDo toDo = new ToDo("return book", true);
        toDo.unmark();
        assertEquals("[T][ ] return book", toDo.toString());
    }

    /**
     * Tests if ToDo task is correctly marked.
     */
    @Test
    public void testMark() {
        ToDo toDo = new ToDo("return book");
        toDo.mark();
        assertEquals("[T][X] return book", toDo.toString());
    }

}
