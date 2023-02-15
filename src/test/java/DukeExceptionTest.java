import duke.DukeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class DukeExceptionTest {
    @Test
    public void testExceptionString() {
        Assertions.assertEquals("Beep boop.",
                new DukeException("Beep boop.").toString());
    }
}
