package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Event class
 */
public class ToDoTest {
    /**
     * This checks whether the todo string is valid
     */
    @Test
    public void checkToDoString(){
        Task t = new Task("todo read book");
        assertEquals("[ ] todo read book", t.toString());
    }
}