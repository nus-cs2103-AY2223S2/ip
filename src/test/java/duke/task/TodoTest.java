package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testT0SaveFormat() {
        Todo todo = new Todo("Clean the room");
        assertEquals("T||0||Clean the room", todo.toSaveFormat());
    }

//    @Test
//    public void testFromSaveFormat() {
//        Todo todo = Todo.fromSaveFormat("T||0||Clean the room");
//        assertEquals("[T][ ] Clean the room", todo.toString());
//    }
}
