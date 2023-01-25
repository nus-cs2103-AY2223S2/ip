import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void testDeleteTask() {
        String s = "Noted I've removed this task:\n "
                + "[T][ ] go toilet\n" + "Now you have "
                + 0
                + " tasks in the list";
        assertEquals(s, new ToDo( " go toilet").removeTask());
    }

    @Test
    void testToString() {
        String s = "Got it. I've added this task:\n " + "[T][ ] go toilet\n" + "Now you have " + 1 + " tasks in the list";
        Task task = new ToDo(" go toilet");
        assertEquals(s, task.toString());
        task.removeTask();

    }
}