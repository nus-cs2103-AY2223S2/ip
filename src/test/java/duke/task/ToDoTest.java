package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toDoMethodTest() {
        ToDo tdTest = new ToDo("read book", true);
        String expectedString = "[T][X] read book";
        assertEquals(expectedString, tdTest.toString());
    }
}
