package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testValidDate(){
        Deadline deadline = new Deadline("do this", "2023-02-05");
        String expected = "[D][ ] do this (by: Feb 05 2023)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testNonDate(){
        Deadline deadline = new Deadline("do that", "tomorrow");
        String expected = "[D][ ] do that (by: tomorrow)";
        assertEquals(expected, deadline.toString());
    }
}