package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    private ToDo toDoOne = new ToDo("work");
    private ToDo toDoTwo = new ToDo("homework");
    private ToDo toDoThree = new ToDo(null);
    private ToDo toDoFour = new ToDo("work");

    /**
     * Checks the implementation of ToDo.equals().
     */
    @Test
    public void equals() {
        assertEquals(toDoOne.equals(toDoTwo), false);
        assertEquals(toDoTwo.equals(toDoTwo), true);
        assertEquals(toDoOne.equals(toDoFour), true);
        assertEquals(toDoThree.equals(toDoTwo), false);
    }

    /**
     * Checks the message from ToDo.toString().
     */
    @Test
    public void toString_checkMessage() {
        assertEquals(toDoOne.toString(), "[T][ ] work");
        assertEquals(toDoTwo.toString(), "[T][ ] homework");
    }
}
