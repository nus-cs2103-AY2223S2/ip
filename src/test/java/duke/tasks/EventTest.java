package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import duke.tasks.Event;

public class EventTest {
    @Test
    public void testToString(){
        assertEquals("[E][ ] finish homework (from: 2pm to: 4pm)", new Event("finish homework", "from 2pm to 4pm").toString());
    }

}