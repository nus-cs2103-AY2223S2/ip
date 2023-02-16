package iris.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import iris.exception.DateTimeException;
import iris.task.Event;

public class EventTest {
    @Test
    public void testStringConversion_correctDate_success() throws DateTimeException {
        assertEquals("[E][ ] meeting (from: 29 Jan 2022 03:00 PM, to: 29 Jan 2022 06:00 PM)",
                new Event("meeting", "29-01-2022 1500", "29-01-2022 1800").toString());
    }

    @Test
    public void testStorageFormat_correctDate_success() throws DateTimeException {
        assertEquals("E| |meeting|29-01-2022 1500|29-01-2022 1800\n",
                new Event("meeting", "29-01-2022 1500", "29-01-2022 1800").storageFormat());
    }

    @Test
    public void testStorageFormat_incorrectDate_exceptionThrown1() {
        try {
            assertEquals("",
                    new Event("meeting", "29-01/2022 1500", "29-01-2022 1800").storageFormat());
            fail();
        } catch (DateTimeException e) {
            assertEquals("OOPS!!! Date/time cannot be understood. Type \"help\" to see the commands.",
                    e.getMessage());
        }
    }

    @Test
    public void testStorageFormat_incorrectDate_exceptionThrown2() {
        try {
            assertEquals("",
                    new Event("meeting", "29-01-2022 1500", "29-01/2022 1800").storageFormat());
            fail();
        } catch (DateTimeException e) {
            assertEquals("OOPS!!! Date/time cannot be understood. Type \"help\" to see the commands.",
                    e.getMessage());
        }
    }

    @Test
    public void testStringConversion_incorrectDate_exceptionThrown1() {
        try {
            assertEquals("",
                    new Event("meeting", "29-01-2022 1500", "29-01/2022 1800").toString());
            fail();
        } catch (DateTimeException e) {
            assertEquals("OOPS!!! Date/time cannot be understood. Type \"help\" to see the commands.",
                    e.getMessage());
        }
    }

    @Test
    public void testStringConversion_incorrectDate_exceptionThrown2() {
        try {
            assertEquals("",
                    new Event("meeting", "29-01/2022 1500", "29-01-2022 1800").toString());
            fail();
        } catch (DateTimeException e) {
            assertEquals("OOPS!!! Date/time cannot be understood. Type \"help\" to see the commands.",
                    e.getMessage());
        }
    }
}
