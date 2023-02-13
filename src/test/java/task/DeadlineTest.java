package task;

import dukeexception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private String input = "deadline buy book /by 2018-09-08 1800";

    @Test
    public void storeTaskString() {
        try {
            Deadline deadline = Deadline.generate(input);
            assertEquals(deadline.storeTaskString(), "D | 0 | buy book | Sep 08 2018 18:00");
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getTaskTypeTest() {
        try {
            Deadline deadline = Deadline.generate(input);
            assertEquals(deadline.getTaskType(), "D");
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }
}
