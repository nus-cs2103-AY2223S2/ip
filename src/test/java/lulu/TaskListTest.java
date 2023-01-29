package lulu;

import lulu.task.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testRecentDescription() {
        TaskList list = new TaskList();
        list.add(new Todo("run"));
        assertEquals(new Todo("run").toString(), list.getRecentTaskDescription());
    }

}
