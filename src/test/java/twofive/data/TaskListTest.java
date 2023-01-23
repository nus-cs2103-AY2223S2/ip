package twofive.data;

import org.junit.jupiter.api.Test;
import twofive.task.Deadline;
import twofive.task.Task;
import twofive.task.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void getTasksListTest() {
        TaskList tasks = new TaskList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Task todoTask = new ToDo("read book");
        tasks.addTask(todoTask);

        LocalDateTime deadline = LocalDateTime.parse("2023-06-06 06:06", formatter);
        Deadline deadlineTask = new Deadline("return book", deadline);
        tasks.addTask(deadlineTask);

        String sampleTasksString = "Here are the tasks in your list:\n1. [T][ ] read book\n2. [D][ ] return book (by: Tue Jun 6 2023 06:06AM)";
        assertEquals(sampleTasksString, tasks.getTasksList());
    }

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
    public void getTasksOnDateListTest() {
        TaskList tasks = new TaskList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime deadline = LocalDateTime.parse("2023-06-06 06:06", formatter);
        Deadline deadlineTask = new Deadline("return book", deadline);
        tasks.addTask(deadlineTask);

        LocalDate sampleDate = LocalDate.parse("2023-06-06", dateOnlyFormatter);
        String sampleSaveTasksString = "Here are the tasks in your list due on Tue Jun 6 2023:\n1. [D][ ] return book (by: Tue Jun 6 2023 06:06AM)";
        assertEquals(sampleSaveTasksString, tasks.getTasksOnDateList(sampleDate));
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
