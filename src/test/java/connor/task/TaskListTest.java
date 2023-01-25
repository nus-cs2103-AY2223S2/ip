package connor.task;

import connor.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    //https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    @Test
    public void addTaskTest() {
        TaskList tasks = new TaskList();
        assertEquals(tasks.getSize(), 0);
        tasks.addTask(new Todo("work"));
        assertEquals(tasks.getSize(), 1);
        tasks.addTask(new Deadline("sleep", "2020-05-05 2000"));
        assertEquals(tasks.getSize(), 2);
        tasks.addTask(new Event("dinner", "2020-05-05 1800", "2020-05-05 2200"));
        assertEquals(tasks.getSize(), 3);
    }

    @Test
    public void deleteInvalidTaskTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("work"));
        tasks.addTask(new Deadline("sleep", "2020-05-05 2000"));
        tasks.addTask(new Event("dinner", "2020-05-05 1800", "2020-05-05 2200"));
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
        tasks.addTask(new Deadline("sleep", "2020-05-05 2000"));
        tasks.addTask(new Event("dinner", "2020-05-05 1800", "2020-05-05 2200"));
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
        tasks.addTask(new Deadline("sleep", "2020-05-05 2000"));
        tasks.addTask(new Event("dinner", "2020-05-05 1800", "2020-05-05 2200"));
        tasks.markDone(1, new Ui());
        assertEquals(tasks.getList().get(0).toString(), "[T][X] work");
    }

    @Test
    public void markUndoneTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("work"));
        tasks.addTask(new Deadline("sleep", "2020-05-05 2000"));
        tasks.addTask(new Event("dinner", "2020-05-05 1800", "2020-05-05 2200"));
        tasks.markDone(1, new Ui());
        assertEquals(tasks.getList().get(0).toString(), "[T][X] work");
        tasks.markUndone(1, new Ui());
        assertEquals(tasks.getList().get(0).toString(), "[T][ ] work");
    }

    @Test
    public void printListTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("work"));
        tasks.addTask(new Deadline("sleep", "2020-05-05 2000"));
        tasks.printList();
        String ans = "        ________________________________________________________\r\n" +
                "\r\n" +
                "        1.[T][ ] work\r\n" +
                "        2.[D][ ] sleep (by: MAY 5 2020 2000)\r\n" +
                "        I have 2 tasks in my memory\r\n";
        assertEquals(outContent, ans);
    }
}
