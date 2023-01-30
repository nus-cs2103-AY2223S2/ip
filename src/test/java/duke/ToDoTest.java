package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests all public methods to create a ToDo task object
 */

public class ToDoTest {
    /**
     * Tests a valid ToDo object creation with public constructor
     */
    @Test
    public void validToDoTest() {
        ToDo todoTest = new ToDo("val");
        String actualUi = todoTest.toString();
        String expectedUi =  "[T][ ] val";
        assertEquals(expectedUi, actualUi);
    }
}
