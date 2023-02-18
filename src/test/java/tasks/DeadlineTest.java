package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import exceptions.NoTaskDescriptionException;


public class DeadlineTest {

    private String taskName = "CS2103 iP";
    private LocalDateTime ldt = LocalDateTime.parse("2022-03-04T04:00");

    @Test
    public void initialisationTest() {
        String actual = "";
        try {
            Deadline deadline = new Deadline(taskName, ldt);
            actual = deadline.toString();
        } catch (NoTaskDescriptionException e) {
            actual = e.getMessage();
        }

        String expected = "[D] [ ] CS2103 iP ( by: MAR 4 2022, FRI, 04:00AM )";
        assertEquals(expected, actual);
    }

    @Test
    public void nullNameTest() {
        String actual = "";
        try {
            Deadline deadline = new Deadline("", ldt);
            actual = deadline.toString();
        } catch (NoTaskDescriptionException e) {
            actual = e.getMessage();
        }

        String expected = "OOPS!!! The description of a Deadline task cannot be empty!";
        assertEquals(expected, actual);
    }

    @Test
    public void containsTest() {
        String actual = "";
        try {
            Deadline deadline = new Deadline(taskName, ldt);
            boolean act = deadline.contains(LocalDate.parse("2022-03-04"));
            actual = String.valueOf(act);
        } catch (NoTaskDescriptionException e) {
            actual = e.getMessage();
        }

        String expected = "true";
        assertEquals(expected, actual);
    }
}
