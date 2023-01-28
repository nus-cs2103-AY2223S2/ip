package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void dummyTest() {
        Task t = new Deadline("return book", "2019-10-15", "1800");
        assertEquals("[D][ ] return book (by: Oct 15 2019 06:00 PM)", t.toString());
    }
}