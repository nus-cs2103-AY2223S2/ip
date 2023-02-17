package lele.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class DeadlineTest {

    @Test
    public void correctDateFormatTest() {
        Deadline deadline = new Deadline("This is a deadline test", "2/12/2019", "1800");
        assertEquals("[D][ ] This is a deadline test(by: Feb 12 2019 1800)", deadline.toString());
    }
}
