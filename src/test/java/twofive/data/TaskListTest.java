package twofive.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import twofive.task.Deadline;
import twofive.task.Task;
import twofive.task.ToDo;

public class TaskListTest {
    @Test
    public void getTasksNum() {
        TaskList tasks = new TaskList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Task todoTask = new ToDo("read book");
        tasks.addTask(todoTask);

        LocalDateTime deadline = LocalDateTime.parse("2023-06-06 06:06", formatter);
        Deadline deadlineTask = new Deadline("return book", deadline);
        tasks.addTask(deadlineTask);

        assertEquals(2, tasks.getTasksNum());
    }

    @Test
    public void getSaveTasksStringTest() {
        TaskList tasks = new TaskList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Task todoTask = new ToDo("read book");
        tasks.addTask(todoTask);

        LocalDateTime deadline = LocalDateTime.parse("2023-06-06 06:06", formatter);
        Deadline deadlineTask = new Deadline("return book", deadline);
        tasks.addTask(deadlineTask);

        String sampleSaveTasksString = "T | 0 | read book\nD | 0 | return book | 2023-06-06 06:06\n";
        assertEquals(sampleSaveTasksString, tasks.getSaveTasksString());
    }
}
