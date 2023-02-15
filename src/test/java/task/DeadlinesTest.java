package test.java.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import task.Deadlines;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DeadlineTest {

    @Test
    void deadlineTestWithDate() {
        Deadlines test = new Deadlines("deadline", LocalDateTime.now());
        assertTrue(test.toString().contains("[D][] deadline"), "toString() method works");

        test.mark();
        assertTrue(test.toString().contains("[D][X] deadline"), "markAsDone() method works");
    }
}