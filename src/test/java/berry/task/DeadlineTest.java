package berry.task;

import berry.exception.IncorrectDateException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toString_success() throws IncorrectDateException {
        final String validDate = "2022-02-02";
        Deadline deadline = new Deadline("some deadline", validDate);
        assertEquals("[D][ ] some deadline (by: Feb 2 2022)", deadline.toString());
    }

    @Test
    public void interpretTaskToString() throws IncorrectDateException {
        final String validDate = "2022-02-02";
        Deadline deadline = new Deadline("some deadline", validDate);
        assertEquals("D |   | some deadline | 2022-02-02", deadline.interpretTaskToString());
    }
}
