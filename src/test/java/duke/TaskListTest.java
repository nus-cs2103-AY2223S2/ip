package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(new EventStub("event competition /from 22/10/2022 0800 /to 22/10/2022 1700"));
        String expected = "1. [E][ ] competition (from: Oct 22 2022 0800 to: Oct 22 2022 1700)";
        assertEquals(expected, taskList.toString());

        taskList.addTask(new EventStub("event competition /from 22/10/2022 0800 /to 22/10/2022 1700"));
        expected += "\n2. [E][ ] competition (from: Oct 22 2022 0800 to: Oct 22 2022 1700)";
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void testMarkTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(new EventStub("event competition /from 22/10/2022 0800 /to 22/10/2022 1700"));
        String expected = "1. [E][ ] competition (from: Oct 22 2022 0800 to: Oct 22 2022 1700)\n";
        taskList.addTask(new EventStub("event competition /from 22/10/2022 0800 /to 22/10/2022 1700"));
        expected += "2. [E][ ] competition (from: Oct 22 2022 0800 to: Oct 22 2022 1700)";

        taskList.markTask(0);
        taskList.markTask(1);
        expected = expected.replace("[ ]", "[X]");
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(new EventStub("event competition /from 22/10/2022 0800 /to 22/10/2022 1700"));
        String expected = "1. [E][ ] competition (from: Oct 22 2022 0800 to: Oct 22 2022 1700)";
        assertEquals(expected, taskList.toString());

        taskList.deleteTask(0);
        assertEquals("", taskList.toString());
    }

    @Test
    public void testUnMarkTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(new EventStub("event competition /from 22/10/2022 0800 /to 22/10/2022 1700"));
        String expected = "1. [E][X] competition (from: Oct 22 2022 0800 to: Oct 22 2022 1700)";
        taskList.markTask(0);
        assertEquals(expected, taskList.toString());

        taskList.unMarkTask(0);
        expected = expected.replace("[X]", "[ ]");
        assertEquals(expected, taskList.toString());
    }
}
