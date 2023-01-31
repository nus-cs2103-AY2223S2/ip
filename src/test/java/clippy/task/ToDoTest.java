package clippy.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    ToDo createTestToDo() {
        return new ToDo("read book");
    }
    @Test
    void toStringFormatTest() {
        ToDo todo = createTestToDo();
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    void getCsvStringTest() {
        ToDo todo = createTestToDo();
        assertEquals("T,read book,false",todo.getCsvString());
    }
}
