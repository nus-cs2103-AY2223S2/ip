package dude.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import dude.exception.DudeException;

public class TaskListTest {
    @Test
    public void getTask_success() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Todo("Todo"));
        taskList.add(new Deadline("Deadline", "2023-01-19 1800"));
        taskList.add(new Event("Event", "2023-01-19 1800", "2023-01-20 1800"));
        TaskList tasks = new TaskList(taskList);
        assertEquals("[D][ ] Deadline (by: Jan 19 2023 18:00)", tasks.getTask(2).toString());
    }

    @Test
    public void toRaw_success() throws DudeException {
        List<Task> taskList = new ArrayList<>();
        TaskList tasks = new TaskList(taskList);
        tasks.addTask(new Todo("Todo"));
        tasks.addTask(new Deadline("Deadline", "2023-01-19 1800"));
        tasks.addTask(new Event("Event", "2023-01-19 1800", "2023-01-20 1800"));
        assertEquals("T | 0 | Todo\n"
                + "D | 0 | Deadline | 2023-01-19 1800\n"
                + "E | 0 | Event | 2023-01-19 1800 | 2023-01-20 1800\n", tasks.toRaw());
    }

    @Test
    public void toString_notEmpty_success() throws DudeException {
        List<Task> taskList = new ArrayList<>();
        TaskList tasks = new TaskList(taskList);
        tasks.addTask(new Todo("Todo"));
        tasks.addTask(new Deadline("Deadline", "2023-01-19 1800"));
        tasks.addTask(new Event("Event", "2023-01-19 1800", "2023-01-20 1800"));
        assertEquals("Here are the tasks in your list: \n"
                + "1.[T][ ] Todo\n"
                + "2.[D][ ] Deadline (by: Jan 19 2023 18:00)\n"
                + "3.[E][ ] Event (from: Jan 19 2023 18:00 to: Jan 20 2023 18:00)\n", tasks.toString());
    }

    @Test
    public void toString_empty_success() {
        List<Task> taskList = new ArrayList<>();
        TaskList tasks = new TaskList(taskList);
        assertEquals("Eh... You currently got no task leh.\n", tasks.toString());
    }
}
