package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toDoToStringTest(){
        ToDo eve = new ToDo("Learning gravity");
        assertEquals("[T][ ] Learning gravity", eve.toString());
    }

}
