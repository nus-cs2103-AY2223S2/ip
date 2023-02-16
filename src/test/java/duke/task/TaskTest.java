package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void toString_validInput_success() {
        assertEquals("[ ] read book", new Task("read book").toString());
    }

    @Test
    public void getRawTask_validInput_success() {
        assertEquals("T ~ 0 ~ read book\n", new Task("read book").getRawTask());
    }

    @Test
    public void getUrgentMessage_validTypesOfDateTime_success() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime expiredDateTime = LocalDateTime.parse("2020-10-01 12:33", formatter);
        assertEquals("\n!! TASK EXPIRED! !!", new Task("read book").getUrgentMessage(expiredDateTime));

        LocalDateTime futureDateTime = LocalDateTime.parse("2030-10-01 12:33", formatter);
        assertEquals("", new Task("read book").getUrgentMessage(futureDateTime));
    }
}
