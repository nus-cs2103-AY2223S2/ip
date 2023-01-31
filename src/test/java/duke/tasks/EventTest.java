import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Event;

public class EventTest {
    @Test
    public void toString_fromToDatesNotDateObjects_useUserInputStringFromTo() {
        assertEquals("[E][ ] test event (from: non-date fromDate to: non-date toDate)",
                new Event("test event", "non-date fromDate", "non-date toDate", false).toString());
    }

    @Test
    public void toString_fromToDatesAreDateObjects_formattedFromToDates() {
        assertEquals("[E][ ] test event (from: Aug 9 1965 to: Aug 10 1965)",
                new Event("test event", "1965/08/09", "1965/08/10", false).toString());
    }

}
