package babe;


import babe.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    private final TaskList taskList = new TaskList();

    @Test
    /**
     * A unit test for TaskList#addToDo(String, boolean) method
     */
    public void addToDoTest_toMarkIsFalse_TaskNotMarked() {
        String taskString = taskList.addToDo("test", false);
        assertEquals("[ ] test", taskString);
    }

    @Test
    /**
     * A unit test for TaskList#addToDo(String, boolean) method
     */
    public void addToDoTest_toMarkIsTrue_TaskMarked() {
        String taskString = taskList.addToDo("test", true);
        assertEquals("[X] test", taskString);
    }

}
