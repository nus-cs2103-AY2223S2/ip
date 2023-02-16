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
    }

    @Test
    public void mark_validIndex_markedTask() throws BobException {
        Todo t = new Todo("test");
        this.tasks.add(t);
        tasks.mark(1);
        boolean isMarked = tasks.get(1).isDone;
        assertEquals(isMarked, true);
    }

    @Test
    public void unmark_outOfRangeIndex_throwException() {
        Todo t = new Todo("test");
        this.tasks.add(t);
        int invalidIndex = 2;
        BobException e = assertThrows(BobException.class, () -> tasks.mark(invalidIndex));
        assertEquals("Error: Index given should be in range [1-1]", e.getMessage());
    }

    @Test
    public void delete_emptyList_throwException() {
        int deleteIndex = 1;
        BobException e = assertThrows(BobException.class, () -> tasks.delete(deleteIndex));
        assertEquals("Error: Task list is empty!", e.getMessage());
    }
}
