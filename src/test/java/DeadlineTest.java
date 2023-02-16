package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        Deadline deadline = new Deadline("Math Assignment", LocalDateTime.of(2020, 10, 17, 1, 27));
        assertEquals("D |   | Math Assignment | by: Oct 17 2020 01:27", deadline.toString());
    }
}
