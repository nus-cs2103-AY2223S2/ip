package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.Test;


public class DeadlineTest {
    @Test
    public void createDeadlineTest() throws ParseException {
        assertEquals("[D][] TASK DESCRIPTION (by: Tue, Jan 31 2023, 11:59 PM)",
                new Deadline("TASK DESCRIPTION", "31/01/2023 2359").toString());
    }

    @Test
    public void saveDeadlineTest() throws ParseException {
        assertEquals("[D][] TASK DESCRIPTION (by: 31/01/2023 2359)",
                new Deadline("TASK DESCRIPTION", "31/01/2023 2359").save());
    }
}
