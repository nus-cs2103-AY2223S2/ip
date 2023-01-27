package panav.task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {

    @Test
    public void getLength_randomTasks_success() {
        TaskList list = new TaskList();
        list.addTask(new Deadline("lots of work", "tonight"));
        list.addTask(new Event("unicon", "sat 4pm", "8pm"));
        assertEquals(2, list.getLength());
    }

    @Test
    public void getTask_singleTask_success() {
        TaskList list = new TaskList();
        Task test = new Event("Unicon", "4pm", "6pm");
        list.addTask(test);
        assertEquals(test, list.getTask(0));
    }


}
