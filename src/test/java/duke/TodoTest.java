package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void descriptionTest() {
        Todo todo = new Todo("Hello World");
        assertEquals("Hello World", todo.getDescription());
    }
}
