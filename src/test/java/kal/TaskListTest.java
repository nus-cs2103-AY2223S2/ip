package kal;

import kal.commands.tasks.Task;
import kal.commands.tasks.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    @Test
    void addTest() {
        TaskList tasks = new TaskList();
        Task toAdd = new ToDo("go to class");
        tasks.add(toAdd);
        assertEquals(toAdd.toString(), tasks.get(0).toString());
    }

    @Test
    void removeTest() {
        TaskList tasks = new TaskList();
        Task toAdd = new ToDo("go to class");
        tasks.add(toAdd);
        tasks.remove(0);
        assertEquals(tasks.size(), 0);
    }

    @Test
    void sizeTest() {
        TaskList tasks = new TaskList();
        Task toAdd = new ToDo("go to class");
        tasks.add(toAdd);
        assertEquals(tasks.size(), 1);
    }

    @Test
    void getTest() {
        TaskList tasks = new TaskList();
        Task toAdd = new ToDo("go to class");
        tasks.add(toAdd);
        assertEquals(toAdd.toString(), tasks.get(0).toString());
    }
}