package iris.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import iris.exception.DateTimeException;
import iris.task.Deadline;

public class DeadlineTest {
    @Test
    public void testStringConversion_correctDate_success() throws DateTimeException {
        assertEquals("[D][ ] assignment (by: 29 Jan 2022 11:59 PM)",
                new Deadline("assignment", "29-01-2022 2359").toString());
    }

    @Test
    public void testStorageFormat_correctDate_success() throws DateTimeException {
        assertEquals("D| |assignment|29-01-2022 2359\n",
                new Deadline("assignment", "29-01-2022 2359").storageFormat());
    }

    @Test
    public void testStorageFormat_incorrectDate_exceptionThrown1() {
        try {
            assertEquals("",
                    new Deadline("assignment", "29/01-2022 2359").storageFormat());
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
                    new Deadline("assignment", "29-01-2022 23:59").storageFormat());
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
                    new Deadline("assignment", "29/01-2022 2359").toString());
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
                    new Deadline("assignment", "29-01-2022 23:59").toString());
            fail();
        } catch (DateTimeException e) {
            assertEquals("OOPS!!! Date/time cannot be understood. Type \"help\" to see the commands.",
                    e.getMessage());
        }
    }
}
