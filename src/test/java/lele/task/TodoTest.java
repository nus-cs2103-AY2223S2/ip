package lele.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class TodoTest {

    @Test
    public void correctDescriptionTest() {
        Todo todo = new Todo("This is a todo test");
        assertEquals("[T][ ] This is a todo test", todo.toString());
    }
}
