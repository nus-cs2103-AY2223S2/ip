package hachi.tasks;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoTest {
    @Test
    public void Test1() {
        Task test = new Todo("task");
        assertEquals(test.toString(), "   [T][ ] task");
    }
}
