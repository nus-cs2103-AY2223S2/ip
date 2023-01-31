package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void stringTest() {
        assertEquals(new ToDo("Thing", true).toString(), "[T][X] Thing");
    }
}
