package clippy.task;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    Deadline createTestDeadline() {
        return new Deadline("return books", LocalDate.of(2023, 1, 12));
    }
    @Test
    void toStringFormatTest() {
        Deadline deadline = createTestDeadline();
        assertEquals("[D][ ] return books (by: Thu 12 Jan)",deadline.toString());
    }

    @Test
    void getCsvStringTest() {
        Deadline deadline = createTestDeadline();
        assertEquals("D,return books,false,2023-01-12",deadline.getCsvString());
    }
}
