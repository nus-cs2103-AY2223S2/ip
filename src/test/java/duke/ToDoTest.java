package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void toDoToStringTest() {
        ToDo eve = new ToDo("Learning gravity");
        assertEquals("[T][ ] Learning gravity", eve.toString());
    }

}
