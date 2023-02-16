package elise.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void test1() {
        Event actual = new Event(true, new String[] {"message", "01/12/2019", "infinity"});
        assertEquals(actual.toString(), "message (from: Dec 01 2019 23:59 to: infinity)");
    }

    @Test
    public void test2() {
        Event actual = new Event(false, new String[] {"message people", "01-12-2019 11:59", "never"});
        assertEquals(actual.fileMessage(), "E||0||message people||01-12-2019 11:59||never\n");
    }
}
