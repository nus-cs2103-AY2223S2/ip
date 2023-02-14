package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskTest {

	@Test
	public void createTaskTest() {
		Task.Event event = new Task.Event("meeting", "3pm", "5pm");
		String eventString = "[E]" + "[" + " " + "] " + "meeting" + " (from: " + "3pm" + " to: " + "5pm" + ")";
		assertEquals(eventString, event.toString());
	}
}
