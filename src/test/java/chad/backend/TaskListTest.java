package chad.backend;

import chad.tasks.Task;
import chad.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    /**
     * Empties the tasks.txt before testing.
     * @throws IOException
     */
    private void clearTasks() throws IOException {
        FileWriter fw = new FileWriter(new File("./tasks.txt"));
        fw.write("");
        fw.flush();
        fw.close();
    }
    @Test
    public void markTask_UnmarkedTodoIndex_MarkedTodo() throws IOException {
        clearTasks();
        Todo t = new Todo("Hello");
        TaskList list = new TaskList();
        list.add(t);
        assertTrue(list.mark(0).isDone());
    }

    @Test
    public void unmarkTask_markedTodoIndex_unmarkedTodo() throws IOException {
        clearTasks();
        Todo t = new Todo("Hello");
        t.mark();
        TaskList list = new TaskList();
        list.add(t);
        assertFalse(list.unmarkIdx(0).isDone());
    }

    @Test
    public void deleteTask_deleteAnIndex_TaskAtIndexDeleted() throws IOException {
        clearTasks();
        Task t1 = new Todo("Task 1");
        Task t2 = new Todo("Task 2");
        Task t3 = new Todo("Task 3");
        TaskList list = new TaskList();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        assertEquals(t2, list.delete(1));
        assertEquals(t3, list.getWholeList().get(1));
    }
}
