package connor.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import connor.ui.Ui;

/**
 * Testcases for the methods present in TaskList.
 */
public class TaskListTest {

    @Test
    public void addTaskTest() {
        TaskList tasks = new TaskList();
        assertEquals(tasks.getSize(), 0);
        tasks.addTask(new Todo("work"));
        assertEquals(tasks.getSize(), 1);
        tasks.addTask(new Deadline("sleep", LocalDateTime.parse("2020-05-05T20:00:00")));
        assertEquals(tasks.getSize(), 2);
        tasks.addTask(new Event("dinner", LocalDateTime.parse("2020-05-05T18:00:00"),
                LocalDateTime.parse("2020-05-05T20:00:00")));
        assertEquals(tasks.getSize(), 3);
    }

    @Test
    public void deleteInvalidTaskTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("work"));
        tasks.addTask(new Deadline("sleep", LocalDateTime.parse("2020-05-05T20:00:00")));
        tasks.addTask(new Event("dinner", LocalDateTime.parse("2020-05-05T18:00:00"),
                LocalDateTime.parse("2020-05-05T20:00:00")));
        try {
            tasks.deleteTask("5", new Ui());
        } catch (InvalidTaskException e) {
            assertEquals(e.getMessage(), new InvalidTaskException().getMessage());
        }
    }

    @Test
    public void deleteValidTaskTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("work"));
        tasks.addTask(new Deadline("sleep", LocalDateTime.parse("2020-05-05T20:00:00")));
        tasks.addTask(new Event("dinner", LocalDateTime.parse("2020-05-05T18:00:00"),
                LocalDateTime.parse("2020-05-05T20:00:00")));
        try {
            tasks.deleteTask("2", new Ui());
        } catch (InvalidTaskException e) {
            assertEquals(1, 2);
        }
        assertEquals(tasks.getSize(), 2);
    }

    @Test
    public void markDoneTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("work"));
        tasks.addTask(new Deadline("sleep", LocalDateTime.parse("2020-05-05T20:00:00")));
        tasks.addTask(new Event("dinner", LocalDateTime.parse("2020-05-05T18:00:00"),
                LocalDateTime.parse("2020-05-05T20:00:00")));
        try {
            tasks.markDone(1, new Ui());
        } catch (InvalidTaskException e) {
            System.out.println("something went wrong here");
        }
        assertEquals(tasks.getList().get(0).toString(), "[T][X] work");
    }

    @Test
    public void markUndoneTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("work"));
        tasks.addTask(new Deadline("sleep", LocalDateTime.parse("2020-05-05T20:00:00")));
        tasks.addTask(new Event("dinner", LocalDateTime.parse("2020-05-05T18:00:00"),
                LocalDateTime.parse("2020-05-05T20:00:00")));
        try {
            tasks.markDone(1, new Ui());
            assertEquals(tasks.getList().get(0).toString(), "[T][X] work");
            tasks.markUndone(1, new Ui());
            assertEquals(tasks.getList().get(0).toString(), "[T][ ] work");
        } catch (InvalidTaskException e) {
            System.out.println("something went wrong here");
        }
    }

    @Test
    public void toStringTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("work"));
        tasks.addTask(new Deadline("sleep", LocalDateTime.parse("2020-05-05T20:00:00")));
        String ans = "1.[T][ ] work\n"
                + "2.[D][ ] sleep (by: MAY 5 2020 2000)\n"
                + "I have 2 tasks in my memory\n";
        assertEquals(tasks.toString(), ans);
    }

    @Test
    public void findTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("work"));
        tasks.addTask(new Deadline("sleep", LocalDateTime.parse("2020-05-05T20:00:00")));
        tasks.addTask(new Event("assist with work", LocalDateTime.parse("2020-05-05T18:00:00"),
                LocalDateTime.parse("2020-05-05T20:00:00")));
        String output = tasks.find("work");
        String expected = "Here are the matching results:\n"
                + "  1.[T][ ] work\n"
                + "  2.[E][ ] assist with work (from: MAY 5 2020 1800 to: MAY 5 2020 2000)\n";
        assertEquals(output, expected);
    }
}
