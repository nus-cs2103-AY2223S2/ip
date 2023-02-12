package clippy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    ToDo createTestToDo() {
        return new ToDo("read book");
    }
    @Test
    void toStringFormatTest() {
        ToDo todo = createTestToDo();
        assertEquals("[T][ ] read book (Priority: NONE)", todo.toString());
    }

    @Test
    void getCsvStringTest() {
        ToDo todo = createTestToDo();
        assertEquals("T,read book,false,NONE", todo.getCsvString());
    }
}
