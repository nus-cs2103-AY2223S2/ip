package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void stringTest() {
        ToDo temp = new ToDo("sleep");
        assertEquals("[T] [ ] sleep", temp.toString());
    }
}
