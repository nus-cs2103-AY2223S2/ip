import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import duke.task.Deadline;
import java.time.LocalDate;

public class DeadlineTest {

    @Test
    public void testDeadline() {
        assertEquals("deadline return book /by 2/12/2019 @ 0",
                new Deadline("return book", "deadline return book /by 2/12/2019",
                        LocalDate.parse("2019-12-2")).toString());
    }
}
