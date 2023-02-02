import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import duke.Deadline;
public class TodoTest {

    @Test
    public void testDeadline() {
        assertEquals("[D][ ] return book (by: Dec 2 2019)",
                new Deadline("deadline return book /by 2/12/2019").printTask());
    }
}
