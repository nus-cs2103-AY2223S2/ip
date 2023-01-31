import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;

public class DeadlineTest {
    @Test
    public void toString_dueDateNotDateObject_useUserInputStringFromTo() {
        assertEquals("[D][ ] test deadline (by: non-date byDate)",
                new Deadline("test deadline", "non-date byDate", false).toString());
    }

    @Test
    public void toString_dueDatesIsDateObject_formattedFromToDates() {
        assertEquals("[D][ ] test deadline (by: Nov 19 1999)",
                new Deadline("test deadline", "1999/11/19", false).toString());
    }

}
