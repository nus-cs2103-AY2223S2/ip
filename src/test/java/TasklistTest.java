import duke.*;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasklistTest {
    @Test
    public void taskListTest1() throws ParseException {
        Tasklist tasklist = new Tasklist();
        tasklist.addingActivities("todo", "todo read");
        ArrayList<Task> result = new ArrayList<Task>();
        result.add(new Todo("read", "todo read"));
        assertEquals(result.get(0).toString(), tasklist.getList().get(0).toString());
    }

    @Test
    public void taskListTest2() throws ParseException{
        Tasklist tasklist = new Tasklist();
        tasklist.addingActivities("event", "event project meeting /from Mon 2pm /to 4pm");
        ArrayList<Task> result = new ArrayList<Task>();
        result.add(new Event("project meeting ", "event project meeting /from Mon 2pm /to 4pm", "Mon 2pm ", "4pm"));
        assertEquals(result.get(0).toString(), tasklist.getList().get(0).toString());
    }
}