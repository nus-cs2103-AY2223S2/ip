package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void saveTaskTest(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime by = LocalDateTime.parse("14/02/2023 1800", format);
        LocalDateTime from = LocalDateTime.parse("19/02/2023 1000", format);
        LocalDateTime to = LocalDateTime.parse("19/02/2023 1200", format);
        Task task1 = new Todo("return book");
        Task task2 = new Deadline("assignment", by);
        Task task3 = new Event("project meeting", from, to);
        TaskList tasks = new TaskList();
        Ui ui = new Ui();

        tasks.saveTask(ui, "return book");
        tasks.saveTask(ui, "assignment", by);
        tasks.saveTask(ui, "project meeting", from, to);

        assertEquals(task1.toString(), tasks.getListOfTasks().get(0).toString());
        assertEquals(task2.toString(), tasks.getListOfTasks().get(1).toString());
        assertEquals(task3.toString(), tasks.getListOfTasks().get(2).toString());
    }

    @Test
    public void markTaskTest() throws DukeException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime from = LocalDateTime.parse("19/02/2023 1000", format);
        LocalDateTime to = LocalDateTime.parse("19/02/2023 1200", format);
        Task task = new Event("project meeting", from, to);
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        tasks.saveTask(ui, "project meeting", from, to);
        tasks.markTask(ui, 1);

        assertEquals(tasks.getListOfTasks().get(0).getStatusIcon(), "X");
        task.markAsDone();
        assertEquals(task.toString(),
                "[E][X] project meeting (from: Feb 19 2023 10:00 AM to: Feb 19 2023 12:00 PM)");
    }
}
