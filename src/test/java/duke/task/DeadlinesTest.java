package duke.task;

import duke.exception.DukeInvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {
    //test setup
    @Test
    public void getBy_ValidDateTime_DeadlineCreated() throws DukeInvalidArgumentException {
        Deadlines task = new Deadlines("Do Homework", "2023-01-28T23:59:59");
        assertEquals("2023-01-28T23:59:59", task.getDeadline());
    }

    @Test
    public void getDeadline_ValidDateTime_StringReturned() throws DukeInvalidArgumentException {
        Deadlines task = new Deadlines("Do Homework", "2023-01-28T23:59:59");
        assertEquals("[D][0] Do Homework(by: 28-01-2023 23:59:59)", task.toString());
    }
}
