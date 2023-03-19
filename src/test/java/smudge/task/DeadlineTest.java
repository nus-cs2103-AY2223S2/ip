package smudge.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void deadlineTest(){
        Deadline test = new Deadline("deadline", LocalDateTime.parse("2023-03-03T20:20:00"));
        assertEquals("[D][ ] deadline (by: Mar 03 2023 20:20)", test.toString(), "toString() method works");

        test.markDone();
        assertEquals("[D][X] deadline (by: Mar 03 2023 20:20)", test.toString(), "markDone() method works");
    }
}