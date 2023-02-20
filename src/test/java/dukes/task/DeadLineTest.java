package dukes.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeadLineTest {

    @Test
    void testToString() {
        DeadLine deadline = new DeadLine("write homework",
                true, LocalDate.parse("2023-02-03"));
        assertEquals("[D][X] write homework (by: Feb 3 2023)",
                deadline.toString());
    }
}