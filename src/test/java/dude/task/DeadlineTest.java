package dude.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toString_success(){
        Deadline deadline = new Deadline("Deadline", "2023-01-19 1800");
        assertEquals("[D][ ] Deadline (by: Jan 19 2023 18:00)", deadline.toString());
    }

    @Test
    public void toRaw_success(){
        Deadline deadline = new Deadline("Deadline", "2023-01-19 1800");
        assertEquals("D | 0 | Deadline | 2023-01-19 1800\n", deadline.toRaw());
    }
}