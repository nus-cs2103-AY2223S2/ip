import tasks.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    Deadline test = new Deadline("Sleep", "2023-02-17");

    @Test
    public void testToString() {
        assertEquals("[D][ ] Sleep (by: Feb 17 2023)", test.toString());
    }
}
