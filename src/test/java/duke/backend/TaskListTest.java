package duke.backend;

import duke.tasks.Task;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void markTask_UnmarkedTodoIndex_MarkedTodo() throws IOException {
        Todo t = new Todo("Hello");
        TaskList list = new TaskList();
        list.add(t);
        assertTrue(list.mark(0).isDone);
    }

    @Test
    public void unmarkTask_markedTodoIndex_unmarkedTodo() throws IOException {
        Todo t = new Todo("Hello");
        t.mark();
        TaskList list = new TaskList();
        list.add(t);
        assertFalse(list.unmark(0).isDone);
    }

    @Test
    public void deleteTask_deleteAnIndex_TaskAtIndexDeleted() throws IOException {
        Task t1 = new Task("Task 1");
        Task t2 = new Task("Task 2");
        Task t3 = new Task("Task 3");
        TaskList list = new TaskList();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        assertEquals(t2, list.delete(1));
        assertEquals(t3, list.getWholeList().get(1));
    }
}
