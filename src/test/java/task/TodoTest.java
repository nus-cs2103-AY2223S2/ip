package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toString_task_string() {
        assertEquals("[T][ ] get book from library", new Todo("todo get book from library").toString());
    }

}