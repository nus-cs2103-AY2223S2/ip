package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.Test;


public class EventTest {
    @Test
    public void createEventTest() throws ParseException {
        assertEquals("[E][] TASK DESCRIPTION (from: Mon, Jan 30 2023, 8:00 AM to: Tue, Jan 31 2023, 8:00 PM)",
                new Event("TASK DESCRIPTION", "30/01/2023 0800", "31/01/2023 2000").toString());
    }

    @Test
    public void saveDeadlineTest() throws ParseException {
        assertEquals("[E][] TASK DESCRIPTION (from: 30/01/2023 0800 to: 31/01/2023 2000)",
                new Event("TASK DESCRIPTION", "30/01/2023 0800", "31/01/2023 2000").save());
    }
}
