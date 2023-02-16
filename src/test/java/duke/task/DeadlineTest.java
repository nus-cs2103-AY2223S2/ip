package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void dummyTest() {
        Task t = new Deadline("return book", "2019-10-15", "1800");
        assertEquals("[D][ ] return book (by: Oct 15 2019 06:00 PM)", t.toString());
    }
}
