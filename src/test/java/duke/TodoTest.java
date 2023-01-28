package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoTest {
    private String description = "borrow book";
    private Todo t = new Todo(description);
    @Test
    public void Test1() {
        assertEquals(t.toString(), "[T][ ] borrow book");
    }
    @Test
    public void Test2() {
        assertEquals(t.toFile(), "T /0 /borrow book");
    }

    @Test
    public void Test3() {
        t.markAsDone();
        assertEquals(t.toString(), "[T][X] borrow book");
    }

    @Test
    public void Test4() {
        t.markAsUndone();
        assertEquals(t.toString(), "[T][ ] borrow book");
    }
}
