package duke;

import duke.tasklist.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToStringComplete() {
        Event task = new Event("This is Event.", "Mon 2pm", "4pm" );
        task.changeCompletion();
        String actualOutput = task.toString();
        assertEquals("[E][X] This is Event.(from:Mon 2pm to:4pm)", actualOutput);
    }
}