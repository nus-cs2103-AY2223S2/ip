package spongebob.task;

import spongebob.exception.SpongebobInvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {
    //test setup
    @Test
    public void getBy_ValidDateTime_DeadlineCreated() throws SpongebobInvalidArgumentException {
        Deadlines task = new Deadlines("Do Homework", "01/01/2023 23:59");
        assertEquals("01/01/2023 23:59", task.getDeadline());
    }

    @Test
    public void getDeadline_ValidDateTime_StringReturned() throws SpongebobInvalidArgumentException {
        Deadlines task = new Deadlines("Do Homework", "01/01/2023 23:59");
        assertEquals("[D] [0] Do Homework (by: 01/01/2023 23:59)", task.toString());
    }
}
