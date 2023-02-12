package willy;

import willy.task.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testEventCreation() {
        assertEquals("[D][ ]deadline return book (by Sunday)",
                new Deadline("deadline return book ", "by Sunday").toString());
    }
}