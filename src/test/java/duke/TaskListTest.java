package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void emptyListTest(){
        TaskList emptyList = new TaskList();
        assertEquals(emptyList.getSize(), 0);
        assertEquals(emptyList.toString(), "Empty List");
    }

    @Test
    public void addTaskTest() {
        TaskList lst = new TaskList();
        lst.addTask();

        assertEquals(lst.getSize(), 1);
        assertEquals(lst.toString(), "[ ] Test\n");

        lst.addTask();
        assertEquals(lst.getSize(), 2);
        assertEquals(lst.toString(), "[ ] Test\n[ ] Test\n");
    }

    @Test
    public void deleteTaskTest() {
        TaskList lst = new TaskList();
        lst.addTask();
        lst.addTask();
        lst.deleteTask(1);

        assertEquals(lst.getSize(), 1);
        assertEquals(lst.toString(), "[ ] Test\n");

        lst.deleteTask(1);
        assertEquals(lst.getSize(), 0);
        assertEquals(lst.toString(), "Empty List");

        lst.addTask();
        lst.addTask();
        lst.deleteTask(2);

        assertEquals(lst.getSize(), 1);
        assertEquals(lst.toString(), "[ ] Test\n");

        lst.deleteTask(1);
        assertEquals(lst.getSize(), 0);
        assertEquals(lst.toString(), "Empty List");
    }
}

