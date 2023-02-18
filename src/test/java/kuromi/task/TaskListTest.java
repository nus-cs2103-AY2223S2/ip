package kuromi.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import kuromi.exception.KuromiException;

public class TaskListTest {
    @Test
    public void taskAdd() throws KuromiException {
        Deadline d = new Deadline("return book", "2024-01-30 15:30");
        ArrayList<Task> inp = new ArrayList<Task>();
        inp.add(new Todo("borrow book"));
        TaskList tasks = new TaskList(inp);
        tasks.add(d);
        Task cur = tasks.get(1);
        assertEquals(cur, d);
    }
    @Test
    public void taskDel() throws KuromiException {
        Deadline d = new Deadline("return book", "2024-01-30 15:30");
        ArrayList<Task> inp = new ArrayList<Task>();
        inp.add(new Todo("borrow book"));
        TaskList tasks = new TaskList(inp);
        tasks.add(d);
        tasks.delete(1);
        assertEquals(1, tasks.size());
    }

    @Test
    public void taskGet() throws KuromiException {
        Deadline d = new Deadline("return book", "2024-01-30 15:30");
        ArrayList<Task> inp = new ArrayList<Task>();
        inp.add(new Todo("borrow book"));
        inp.add(d);
        TaskList tasks = new TaskList(inp);
        assertEquals(inp.get(1), tasks.get(1));
    }
}
