package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {
    private String description = "read book";
    private String by = "2023-01-28";
    private Deadline d = new Deadline(description, by);
    @Test
    public void Test1() {
        assertEquals(d.toString(), "[D][ ] read book (by: Jan 28 2023)");
    }
    @Test
    public void Test2() {
        assertEquals(d.toFile(), "D /0 /read book /by: 2023-01-28");
    }

    @Test
    public void Test3() {
        d.markAsDone();
        assertEquals(d.toString(), "[D][X] read book (by: Jan 28 2023)");
    }

    @Test
    public void Test4() {
        d.markAsUndone();
        assertEquals(d.toString(), "[D][ ] read book (by: Jan 28 2023)");
    }
}
