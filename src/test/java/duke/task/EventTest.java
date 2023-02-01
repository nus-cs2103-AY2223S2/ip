package duke.task;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event task = new Event("event project week ", LocalDate.parse("2019-11-15"), LocalDate.parse("2019-11-25"));

    @Test
    public void eventTest() {
        assertEquals("[E][ ] event project week [from: Nov 15 2019 to: Nov 25 2019]",task.toString());
    }

    @Test
    public void markEventTest() {
        task.markAsDone();
        assertEquals("[E][X] event project week [from: Nov 15 2019 to: Nov 25 2019]",task.toString());
    }
}
