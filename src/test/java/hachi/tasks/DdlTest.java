package hachi.tasks;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DdlTest {
    @Test
    public void Test1() {
        Task test = new Ddl("task", "2023-01-28");
        assertEquals(test.toString(), "   [D][ ] task (by: Jan 28 2023)");
    }
}
