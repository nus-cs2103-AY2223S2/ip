package TaskTests;

import duke.exceptions.DukeExceptions;
import duke.tasks.Deadline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void inputTest() {
        String input = "deadline abc /by 2023-02-26 1800";
        Deadline deadline = new Deadline();
        try {
            deadline.formatDescription(input);
            assertEquals("abc (by: 2023-02-15 1800", deadline.getDescription());
        } catch(DukeExceptions e){
        }
    }

    @Test
    public void invalidFromToTest() {
        String input = "deadline abc /by 1";
        Deadline deadline = new Deadline();
        try {
            deadline.formatDescription(input);
        } catch(DukeExceptions e){
            assertEquals("Please enter the deadline correctly", e.getMessage());
        }
    }
}
