package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlinesTest {
    @Test
    public void createDeadline() {
        Deadlines created = new Deadlines("Finish drama", "2023-02-15T10:30");
        assertEquals("[D][ ] Finish drama (by: Feb 15 2023 1030)", created.toString());
    }

    @Test
    public void markDeadline() {
        Deadlines created = new Deadlines("Finish drama", "2023-02-15T10:30");
        created.mark();
        assertEquals("[D][X] Finish drama (by: Feb 15 2023 1030)", created.toString());
    }

    @Test
    public void saveDeadline() {
        Deadlines created = new Deadlines("Finish drama", "2023-02-15T10:30");
        assertEquals("D | 0 | Finish drama | 2023-02-15T10:30\n", created.toWrite());
    }

    @Test
    public void isWithinTest() {
        Deadlines created = new Deadlines("Finish drama", "2023-02-15T10:30");
        assertEquals(true, created.isWithinDate(LocalDateTime.parse("2023-01-24T00:00")));
    }
}
