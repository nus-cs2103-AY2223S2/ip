package berry.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import berry.exception.IncorrectDateException;

public class DeadlineTest {
    @Test
    public void toString_checkDeadlineTask_successs() throws IncorrectDateException {
        final String validDate = "2022-02-02";
        Deadline deadline = new Deadline("some task", validDate);
        assertEquals("[D][  ] some task (by: Feb 2 2022)", deadline.toString());
    }

    @Test
    public void interpretTaskToString_deadlineTask_success() throws IncorrectDateException {
        final String validDate = "2022-02-02";
        Deadline deadline = new Deadline("some task", validDate);
        assertEquals("D |    | some task | 2022-02-02", deadline.interpretTaskToText());
    }
}
