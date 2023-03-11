package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private Deadline deadline = new Deadline("Read book", "2019-10-05 1530");
    @Test
    public void initialiseTest() {
        assertEquals(deadline.toString(), "[D][ ] Read book (by: OCT 5 2019, 15:30)");
    }

    @Test
    public void markTaskTest() {
        deadline.setDone();
        assertEquals(deadline.toString(), "[D][X] Read book (by: OCT 5 2019, 15:30)");
    }

    @Test
    public void unmarkTaskTest() {
        deadline.setUndone();
        assertEquals(deadline.toString(), "[D][ ] Read book (by: OCT 5 2019, 15:30)");
    }
}
