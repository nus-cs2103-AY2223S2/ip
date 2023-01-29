package crystal.task;

import crystal.CrystalException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents the DeadlineTest class.
 *
 */
public class DeadlineTest {

    /**
     * test if the deadline class is working as intended
     *
     */
    @Test
    public void testDeadLine() throws CrystalException {
        Deadline dl = new Deadline("return book", "2019-10-15T18:00");
        assertEquals("[D][ ] return book (by: Oct 15 2019 0600 PM)", dl.toString());
    }
}
