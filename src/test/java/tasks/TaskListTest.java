package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static duke.ui.Ui.LS;

import org.junit.jupiter.api.Test;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

public class TaskListTest {
    @Test
    public void numTasksMsg_threeTasksInList_correctString() {
        Task a = new ToDo("a");
        Task b = new ToDo("b");
        Task c = new ToDo("c");
        TaskList tl = new TaskList();
        tl.addTask(a);
        tl.addTask(b);
        tl.addTask(c);

        String expectedMessage = "Now you have 3 tasks in the list.";
        String actualMessage = tl.numTasksMsg();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void formatList_threeTasksInList_correctString() {
        Task a = new ToDo("a");
        Task b = new ToDo("b");
        Task c = new ToDo("c");
        TaskList tl = new TaskList();
        tl.addTask(a);
        tl.addTask(b);
        tl.addTask(c);

        String expectedMessage = "1. [T][ ] a" + LS + "2. [T][ ] b" + LS + "3. [T][ ] c";
        String actualMessage = tl.formatList();

        assertEquals(expectedMessage, actualMessage);
    }
    @Test
    public void toString_stringRepresentationOfSecondTask_correctString() {
        Task a = new ToDo("a");
        Task b = new ToDo("b");
        Task c = new ToDo("c");
        TaskList tl = new TaskList();
        tl.addTask(a);
        tl.addTask(b);
        tl.addTask(c);

        String expectedMessage = "[T][ ] b";
        String actualMessage = tl.toString(1);

        assertEquals(expectedMessage, actualMessage);
    }

}
