package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import duke.tasks.Deadline;

public class DeadlineTest {
    @Test
    public void testToString(){
        assertEquals("[D][ ] finish homework (by: Dec 20 2022)", new Deadline("finish homework", LocalDate.parse("2022-12-20")).toString());
    }

}
