package duke.tasks;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskTest {
    @Test
    public void taskStatusTest() {
        Task todo = new Todo("Read book");
        todo.changeStatus();
        assertEquals(todo.getStatus(), true);
        todo.changeStatus();
        assertEquals(todo.getStatus(), false);
    }

    @Test
    public void taskListTest() {
        ArrayList<Task> taskList = new ArrayList<>();
        TaskList list = new TaskList(taskList);
        Todo todo1 = new Todo("Reach Home");
        Todo todo2 = new Todo("Go school");
        Todo todo3 = new Todo("Go campus");
        Todo todo4 = new Todo("Play tennis");

        list.addTask(todo1);
        list.addTask(todo2);
        list.addTask(todo3);
        list.addTask(todo4);

        assertEquals(list.getListLength(), 4, "List length should be 4");
        list.deleteTask(2);
        assertEquals(list.getListLength(), 3, "List length should be 3 after deletion.");

    }
}
