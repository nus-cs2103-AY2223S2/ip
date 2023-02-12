import duke.MainWindow;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;

/**
 * Test for TaskList class
 */
public class TaskListTest {

    /**
     * Tests whether a todo task can be added successfully.
     */
    @Test
    public void addTodoTask() {
        MainWindow mainWindow = new MainWindow();
        TaskList taskList = new TaskList(new ArrayList<Task>(),mainWindow);
        try {
            String[] description = {"Meet Friends"};
            taskList.addTask(description, "TODO");
            assertEquals((new Todo("Meet Friends")).toString(), taskList.getListOfTask().get(0).toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Tests whether a deadline task can be added successfully.
     */
    @Test
    public void addDeadlineTask() {
        MainWindow mainWindow = new MainWindow();
        TaskList taskList = new TaskList(new ArrayList<Task>(),mainWindow);
        try {
            String[] description = ("Do 2103T iP /by 27/02/2023 2359").split(" ");
            LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2023, 2, 27), LocalTime.of(23, 59));
            taskList.addTask(description, "DEADLINE");
            assertEquals((new Deadline("Do 2103T iP",dateTime)).toString(), taskList.getListOfTask().get(0).toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Tests whether a event task can be added successfully.
     */
    @Test
    public void addEventTask() {
        MainWindow mainWindow = new MainWindow();
        TaskList taskList = new TaskList(new ArrayList<Task>(), mainWindow);
        try {
            String[] description = ("2103T Team project /from 27/03/2023 2359 /to 29/03/2023 0000").split(" ");
            LocalDateTime dateTimeFrom = LocalDateTime.of(LocalDate.of(2023, 3, 27), LocalTime.of(23, 59));
            LocalDateTime dateTimeTo = LocalDateTime.of(LocalDate.of(2023, 3, 29), LocalTime.of(00, 00));
            taskList.addTask(description, "Event");
            assertEquals((new Event("2103T Team project",dateTimeFrom,dateTimeTo)).toString(), taskList.getListOfTask().get(0).toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
