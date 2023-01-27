package app.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void asDataFormat() {
        try {
            Deadline d = new Deadline("complete this task", "2023/01/26 1700");
            d.markAsDone();
            assertEquals("D | 1 | complete this task | by:2023-01-26T17:00",
                    d.asDataFormat());
        } catch (Exception e) {
            fail();
        }

    }

}
