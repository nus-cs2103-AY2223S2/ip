package duke.Tasks;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void addDeadlineTest() {
        try {
            Deadline newDeadline = new Deadline("exercise", "11/1/2011");
            assertEquals("D |   | exercise | Jan 11 2011 0000AM", newDeadline.toString());
        } catch (Exception ignored) {}
    }

    @Test
    public void markDeadlineTest() {
        try {
            Deadline newDeadline = new Deadline("do work", "12/2/2012");
            newDeadline.markDone();
            assertEquals("D | X | do work | Feb 12 2012 0000AM", newDeadline.toString());
        } catch (Exception ignored) {}

    }
}