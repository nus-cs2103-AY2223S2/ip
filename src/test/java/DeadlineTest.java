import tasks.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class used for doing unit tests on Deadline object
 */
public class DeadlineTest {
    /**
     * Unit test for toFileString function
     */
    @Test
    public void testToFileString(){
        assertEquals("D | 0 | task | Nov 11 2023", new Deadline("task", "2023-11-11").toFileString());
    }

    /**
     * Unit test for toString function
     */
    @Test
    public void testToString(){
        assertEquals("[D][ ] task (by:Nov 11 2023)", new Deadline("task", "2023-11-11").toString());
    }
}
