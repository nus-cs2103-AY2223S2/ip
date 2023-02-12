package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents the test class for ToDo.java.
 *
 * @author MrTwit99
 * @since 2023-02-12
 */
public class ToDoTest {
    private Task tempTask = new ToDo("borrow book");

    /**
     * Tests the method getTaskInfoStatus() in ToDo.java.
     */
    @Test
    public void getTaskInfoStatus() {
        tempTask.setDone();
        assertEquals("[T][X] borrow book", tempTask.getTaskInfoStatus());
        System.out.println("Passed 1/1 check of marking tasks: ToDo.getTaskInfoStatus()");
        tempTask.setIncomplete();
        assertEquals("[T][ ] borrow book", tempTask.getTaskInfoStatus());
        System.out.println("Passed 1/1 check of unmarking tasks: ToDo.getTaskInfoStatus()");
    }

    /**
     * Tests the method getTaskInfo() in ToDo.java.
     */
    @Test
    public void getTaskInfo() {
        tempTask.setDone();
        assertEquals("[T][X] borrow book", tempTask.getTaskInfo());
        System.out.println("Passed 1/1 check of marking tasks: ToDo.getTaskInfo()");
        tempTask.setIncomplete();
        assertEquals("[T][ ] borrow book", tempTask.getTaskInfo());
        System.out.println("Passed 1/1 check of unmarking tasks: ToDo.getTaskInfo()");
    }
}
