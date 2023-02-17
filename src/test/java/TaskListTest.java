import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;

public class TaskListTest {
    @Test
    public void markTest() {
        TaskList list  = new TaskList();
        list.add(new Deadline("Say hi", "2019-09-09"));
        list.add(new Event("Say bye", "Now", "Later"));
        list.add(new Todo("cry"));
        list.mark(1);
        assertEquals("[E] [X] Say bye (from: Now to: Later)",
                list.get(1).toString());
    }
}
