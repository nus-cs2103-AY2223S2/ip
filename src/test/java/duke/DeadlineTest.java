package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private String description = "read book";
    private String by = "2023-01-28";
    private Deadline d = new Deadline(description, by);
    @Test
    public void inputParsingTest() {
        assertEquals(d.toString(), "[D][ ] read book (by: Jan 28 2023)");
    }
    @Test
    public void fileParsingTest() {
        assertEquals(d.toFile(), "D /0 /read book /by: 2023-01-28");
    }

    @Test
    public void markAsDoneTest() {
        d.markAsDone();
        assertEquals(d.toString(), "[D][X] read book (by: Jan 28 2023)");
    }

    @Test
    public void markAsUndoneTest() {
        d.markAsUndone();
        assertEquals(d.toString(), "[D][ ] read book (by: Jan 28 2023)");
    }
}
