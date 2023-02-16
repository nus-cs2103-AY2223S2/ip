package elise.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void test1() {
        Deadline actual = new Deadline(true, new String[] {"message", "01/12/2019"});
        assertEquals(actual.toString(), "message (by: Dec 01 2019 23:59)");
    }

    @Test
    public void test2() {
        Deadline actual = new Deadline(true, new String[] {"message people", "01-12-2019 11:59"});
        assertEquals(actual.fileMessage(), "D||1||message people||01-12-2019 11:59\n");
    }
}
