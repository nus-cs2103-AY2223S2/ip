package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void addDeadline() {
        Task midterm = null;
        midterm = new Deadline("CS2109S Midterm Exam ", "2023-01-31");
        assertEquals("[D][ ] CS2109S Midterm Exam (by: Jan 31 2023)", midterm.toString());
    }

    @Test
    public void mark() {
        Task midterm = null;
        midterm = new Deadline("CS2109S Midterm Exam ", "2023-01-31");
        midterm.markAsDone();
        assertEquals("[D][X] CS2109S Midterm Exam (by: Jan 31 2023)", midterm.toString());
    }

    @Test
    public void unmark() {
        Task midterm = null;
        midterm = new Deadline("CS2109S Midterm Exam ", "2023-01-31");
        midterm.markAsDone();
        midterm.markAsUnDone();
        assertEquals("[D][ ] CS2109S Midterm Exam (by: Jan 31 2023)", midterm.toString());
    }
}
