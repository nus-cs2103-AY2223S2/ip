package donkeychat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TaskListTest {

    @Test
    void addTask() {
        TaskList list = new TaskList();
        Task task = new ToDo("test");
        list.addTask(task);
        assertEquals(list.getAtIndex(0),task);
    }

    @Test
    void getSize() {
        TaskList list = new TaskList();
        assertEquals(0,list.getSize());
        list.addTask(new ToDo("test"));
        assertEquals(1,list.getSize());
    }
}