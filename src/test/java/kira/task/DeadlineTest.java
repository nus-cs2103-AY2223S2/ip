package kira.task;

import org.junit.jupiter.api.Test;

import kira.exception.KiraException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTest {
    
    @Test
    public void constructor_wrongDateInput_throwKiraException() {
        try {
            Deadline task = new Deadline("test", "123");
        } catch (KiraException e) {
            assertEquals(
                "Please input your date by this format:"
                    + " yyyy-MM-dd HHmm",
                e.getMessage());
        }
    }

    @Test
    public void constructor_correctDateInput_success() {
        try {
            Deadline task = new Deadline("test", "2000-12-12 2359");
        } catch (KiraException e) {
            assertEquals(
                "Please input your date by this format:"
                    + " yyyy-MM-dd HHmm",
                e.getMessage());
        }
    }
}
