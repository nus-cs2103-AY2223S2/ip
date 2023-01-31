package task;

import org.junit.jupiter.api.Test;
import duke.tasks.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void checkCorrectToString() {
        Deadline testInput = new Deadline("deadline do the dishes /by 15/08/2020 2300");
        assertEquals(testInput.toString(), ". [D][ ] do the dishes (by: 15-Aug-2020)");
    }

    @Test
    public void checkCorrectMarkStatus() {
        Deadline testInput = new Deadline("deadline come back from japan /by 25/01/2022 1900");
        testInput.setMark();
        assertEquals(testInput.toString(), ". [D][X] come back from japan (by: 25-Jan-2022)");
        testInput.setMark();
        assertEquals(testInput.toString(), ". [D][ ] come back from japan (by: 25-Jan-2022)");
    }
}
