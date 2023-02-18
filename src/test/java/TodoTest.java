import tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class used for doing unit tests on Todo objects
 */
public class TodoTest {
    /**
     * Unit test for toFileString function
     */
    @Test
    public void testToFileString(){
        assertEquals("T | 0 | task", new Todo("task").toFileString());
    }

    /**
     * Unit test for toString function
     */
    @Test
    public void testToString(){
        assertEquals("[T][ ] task", new Todo("task").toString());
    }
}
