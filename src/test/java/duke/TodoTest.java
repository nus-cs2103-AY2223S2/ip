package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    private String description = "borrow book";
    private Todo t = new Todo(description);
    @Test
    public void inputParsingTest() {
        assertEquals(t.toString(), "[T][ ] borrow book");
    }
    @Test
    public void fileParsingTest() {
        assertEquals(t.toFile(), "T /0 /borrow book");
    }

    @Test
    public void markAsDoneTest() {
        t.markAsDone();
        assertEquals(t.toString(), "[T][X] borrow book");
    }

    @Test
    public void markAsUndoneTest() {
        t.markAsUndone();
        assertEquals(t.toString(), "[T][ ] borrow book");
    }
}
