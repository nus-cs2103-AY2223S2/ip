package duke;

import duke.Deadline;
import duke.Event;
import duke.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskTest {
    @Test
    public void toDoTest() {
        ToDo task = new ToDo("do something");
        assertNotNull(task);
        assertEquals("[T][ ] do something", task.toString());
    }

    @Test
    public void deadlineTest(){
        Deadline task = new Deadline("reach deadline", "15:30 20-04-2018");
        assertNotNull(task);
        assertEquals("[D][ ] reach deadline (by: 15:30, Friday, Apr 20 2018)", task.toString());
    }

    @Test
    public void eventTest(){
        Event task = new Event("go to event", "12:00 11-11-1234", "14:00 11-11-1234");
        assertNotNull(task);
        assertEquals("[E][ ] go to event (from: 12:00, Saturday, Nov 11 1234 to: 14:00, Saturday, Nov 11 1234)",
                task.toString());
    }
}

