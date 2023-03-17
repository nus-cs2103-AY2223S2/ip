package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
	Todo todo = new Todo("Wash dishes");

	@Test
	public void toStringTest() {
		String expected = "[T][ ] Wash dishes";
		assertEquals(expected, todo.toString());
	}

	@Test
	public void getSavedFormatTest() {
		todo.markAsDone();
		String expected = "T|1|Wash dishes";
		assertEquals(expected, todo.getSavedFormat());
	}
}
