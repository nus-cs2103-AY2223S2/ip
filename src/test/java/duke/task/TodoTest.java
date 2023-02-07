package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TodoTest {
    private Todo task = new Todo("assignment");
    @Test
    public void todoTest() {
        assertEquals("[T][ ] assignment", task.toString());
    }

    @Test
    public void markTodoTask() {
        task.markAsDone();
        assertEquals("[T][X] assignment", task.toString());
    }
}
