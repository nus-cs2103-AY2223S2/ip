package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void dummyTest() {
        Task t = new Event("project meeting", "Aug 6th 2pm", "4pm");
        assertEquals("[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)", t.toString());
    }
}
