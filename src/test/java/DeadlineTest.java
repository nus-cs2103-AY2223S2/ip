import tasks.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    Deadline test = new Deadline("Sleep", "Tonight");

    @Test
    public void testToString() {
        assertEquals("[D][ ] Sleep (by: Tonight) ", test.toString());
    }
}
