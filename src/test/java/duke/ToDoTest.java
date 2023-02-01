package duke;

import exceptions.IncorrectNoOfArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    Task tempTask = new ToDo("borrow book");

    @Test
    public void getTaskInfoStatus() {
        tempTask.setDone();
        assertEquals("[T][X] borrow book", tempTask.getTaskInfoStatus());
        System.out.println("Passed 1/1 check of marking tasks: ToDo.getTaskInfoStatus()");
        tempTask.setIncomplete();
        assertEquals("[T][ ] borrow book", tempTask.getTaskInfoStatus());
        System.out.println("Passed 1/1 check of unmarking tasks: ToDo.getTaskInfoStatus()");
    }

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
