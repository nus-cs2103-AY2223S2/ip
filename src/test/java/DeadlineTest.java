import org.junit.jupiter.api.Test;
import task.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadTest1() {
        Deadline testDead = new Deadline("asdf", "1234");
        testDead.setChecked(true);
        assertEquals(testDead.toString(), "[D] [X] asdf (by: 1234)");
    }
}
