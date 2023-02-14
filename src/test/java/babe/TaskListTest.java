package babe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    private final TaskList taskList = new TaskList();

    @Test
    /**
     * A unit test for TaskList#addToDo(String, boolean) method
     */
    public void addToDoTest_toMarkIsFalse_TaskNotMarked() {
        String taskString = taskList.addToDo("test", false);
        assertEquals("[T] [ ] test", taskString);
    }

    @Test
    /**
     * A unit test for TaskList#addToDo(String, boolean) method
     */
    public void addToDoTest_toMarkIsTrue_TaskMarked() {
        String taskString = taskList.addToDo("test", true);
        assertEquals("[T] [X] test", taskString);
    }

}
