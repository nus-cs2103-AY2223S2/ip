package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void output() {
        Deadline deadline = new Deadline("return book", "Sunday");
        assertEquals("D | 0 | return book | Sunday", deadline.getOutputFormat());
    }

    @Test
    public void string() {
        Deadline deadline = new Deadline("return book", "Sunday");
        assertEquals("[D][ ] return book (by: Sunday)", deadline.toString());
    }
}
