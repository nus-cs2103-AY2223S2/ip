package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.ui.UiController;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    @Test
    public void parseEventFromFileTest() {
        TaskList lst = new TaskList();
        String[] input = new String[]{"T", "X", "this is a test task"};
        lst.parseEventFromFile(input);
        assertEquals(lst.tasks.get(0).getName(), "this is a test task");
        assertEquals("X", lst.tasks.get(0).getStatus());
        assertTrue(lst.tasks.get(0) instanceof ToDo);
    }

    @Test
    public void addTaskToListTest() {
        ByteArrayOutputStream printedString = new ByteArrayOutputStream(); //buf to capture stdout
        PrintStream console = System.out; //saving console buffer
        System.setOut(new PrintStream(printedString));
        TaskList lst = new TaskList();
        Task task = new ToDo("this is a test task");
//        UiController ui = new UiController();
//        lst.addTaskToList(task, ui);
        assertEquals(1, lst.size());
        assertTrue(lst.tasks.get(0) instanceof ToDo);
        assertEquals(lst.tasks.get(0).getName(), "this is a test task");
        System.setOut(console); //restoring stdout to console
    }

}
