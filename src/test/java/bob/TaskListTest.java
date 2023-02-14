package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskList tasks;

    @BeforeEach
    public void init() {
        tasks = new TaskList();
        Todo t = new Todo("test");
        tasks.add(t);
    }

    @Test
    public void mark_invalidIndex_throwException() {
        int invalidIndex = 2;
        BobException e = assertThrows(BobException.class, () -> tasks.mark(invalidIndex));
        assertEquals("Index given should be in range [1-1]", e.getMessage());
    }
}
