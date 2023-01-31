package task;

import org.junit.jupiter.api.Test;
import duke.tasks.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void checkCorrectToString() {
        Event testInput = new Event("event do the dishes /from 15/08/2020 2300 /to 19/10/2022 1500");
        assertEquals(testInput.toString(), ". [E][ ] do the dishes (by: 15-Aug-2020 to: 19-Oct-2022)");
    }

    @Test
    public void checkCorrectMarkStatus() {
        Event testInput = new Event("event come back from japan /from 25/01/2022 1900 /to 11/02/2023 1200");
        testInput.setMark();
        assertEquals(testInput.toString(), ". [E][X] come back from japan (by: 25-Jan-2022 to: 11-Feb-2023)");
        testInput.setMark();
        assertEquals(testInput.toString(), ". [E][ ] come back from japan (by: 25-Jan-2022 to: 11-Feb-2023)");
    }
}
