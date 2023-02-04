package hachi.tasks;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void Test1() {
        Task test = new Deadline("task", "2023-01-28");
        assertEquals(test.toString(), "   [D][ ] task (by: Jan 28 2023)");
    }
}
