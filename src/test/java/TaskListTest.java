import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    /**
     * test for adding Task object
     */
    @Test
    public void addItem() { //adding one item

        TaskList toDoList = new TaskList();

        toDoList.addTask(new Task("Item1","T"));
        toDoList.addTask(new Task("Item2","T"));
        toDoList.addTask(new Task("Item3","T"));

        String actual = toDoList.getTasks().get(0).toString();
        assertEquals("[T][ ] Item1", actual);


        actual = toDoList.getTasks().get(2).toString();
        assertEquals("[T][ ] Item3", actual);
    }

    /**
     * test for adding Deadline Object
     */
    @Test
    public void addItemDeadline() { //adding one item
        try {
            TaskList toDoList = new TaskList();

            SimpleDateFormat converterDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date date = converterDate.parse("1/1/2023 23:59");
            Task task = new Deadline("Test2 ", "D", date, "1/1/2023 23:59");

            toDoList.addTask(task);

            String actual = toDoList.getTasks().get(0).toString();

            System.out.println();
            assertEquals("[D][ ] Test2 (by: 01 Jan 2023 23:59 PM)", actual);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * test for adding Event object
     */
    @Test
    public void addItemEvent() { //adding one item
        try {
            TaskList toDoList = new TaskList();

            SimpleDateFormat converterDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date start = converterDate.parse("1/1/2023 23:59");
            Date end = converterDate.parse("1/2/2023 23:59");
            Task task = new Event("Test3", "E", start, end,"1/1/2023 23:59", "1/2/2023 23:59");

            toDoList.addTask(task);
            String actual = toDoList.getTasks().get(0).toString();

            assertEquals("[E][ ] Test3 (From: 01 Jan 2023 23:59 PM To: 01 Feb 2023 23:59 PM)", actual);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
