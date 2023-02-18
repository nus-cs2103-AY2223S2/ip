import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.ToDo;

public class ToDoTest {
    /** Test the creation of to do task. */
    @Test
    public void addToDoTest() {
        ToDo todo = new ToDo("Eat HDL!!");

        assertEquals("[T][ ] Eat HDL!!", todo.toString());
    }

    /** Test the marking of to do task. */
    @Test
    public void markTest() {
        ToDo todo = new ToDo("Eat HDL!!");
        todo.mark();

        assertEquals("[T][X] Eat HDL!!", todo.toString());
    }

    /** Test the unmarking of to do task. */
    @Test
    public void unmarkTest() {
        ToDo todo = new ToDo("Eat HDL!!");
        todo.mark();
        todo.unmark();

        assertEquals("[T][ ] Eat HDL!!", todo.toString());
    }
}
