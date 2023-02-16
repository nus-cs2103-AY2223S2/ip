package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void addToDoTest() {
        Event task = new Event("project meeting", "Saturday 3pm", "4pm");
        assertEquals("[E][ ] project meeting(from: Saturday 3pm to: 4pm)", task.toString());
    }

    @Test
    public void markTest() {
        Event task = new Event("project meeting", "Saturday 3pm", "4pm");
        task.mark();
        assertEquals("[E][X] project meeting(from: Saturday 3pm to: 4pm)", task.toString());
    }

    @Test
    public void unmarkTest() {
        Event task = new Event("project meeting", "Saturday 3pm", "4pm");
        task.mark();
        task.unmark();
        assertEquals("[E][ ] project meeting(from: Saturday 3pm to: 4pm)", task.toString());
    }
}
