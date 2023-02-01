package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void getFileRepresentation_givenInstance_correctlyGets() {
        Todo todo = new Todo("");  // Empty description
        assertEquals("todo|false|", todo.getFileRepresentation());
        todo.isDone = true;
        assertEquals("todo|true|", todo.getFileRepresentation());

        todo = new Todo("oneword"); // One word description
        assertEquals("todo|false|oneword", todo.getFileRepresentation());
        todo.isDone = true;
        assertEquals("todo|true|oneword", todo.getFileRepresentation());

        todo = new Todo("this is many many many many many many many many words"); // Many word description
        assertEquals("todo|false|this is many many many many many many many many words", todo.getFileRepresentation());
        todo.isDone = true;
        assertEquals("todo|true|this is many many many many many many many many words", todo.getFileRepresentation());
    }

    @Test
    public void toString_givenInstance_correctlyGets() {
        Todo todo = new Todo("");  // Empty description
        assertEquals("[T][ ] ", todo.toString());
    }
}