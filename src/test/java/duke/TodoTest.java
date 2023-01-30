package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * The class to test Todos class.
 */
public class TodoTest {

    /**
     * The test method to test the toString method.
     */
    @Test
    public void toStringTest() {
        Todos todos = new Todos("go home");
        assertEquals(todos.toString(), "[T][ ] go home");
        todos.setStatus(true);
        assertEquals(todos.toString(), "[T][X] go home");
    }

}
