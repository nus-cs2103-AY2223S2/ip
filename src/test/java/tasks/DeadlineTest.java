package tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {
    @Test
    public void addDeadlineTest() {
        Deadline e = new Deadline("return book", "01/07/2022 1822");
        assertEquals("[D][ ] return book (by: 01 Jul 2022 6:22 PM)", e.toString());
    }
}
