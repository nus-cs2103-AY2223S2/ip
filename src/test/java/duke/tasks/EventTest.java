package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventToString(){
        Event e = new Event("run", true, "sun", "mon");
        assertEquals(e.toString(), "[E][X] run (from: sun to: mon)");
    }

}

