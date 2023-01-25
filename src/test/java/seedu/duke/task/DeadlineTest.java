package seedu.duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D] [ ] 123 (by: Dec 12 2019 )",
                    new Deadline("123", "2019-12-12").toString());
    }

    @Test
    public void getBySuccess() {
        assertEquals("2019-12-12", new Deadline("123", "2019-12-12").getBy());
    }
}
