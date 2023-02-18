package reborn.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testTodoStorageStr() {
        Todo todo = new Todo("Do homework");
        assertEquals("T | 0 | Do homework", todo.storageStr());
        todo.mark();
        assertEquals("T | 1 | Do homework", todo.storageStr());
    }
}
