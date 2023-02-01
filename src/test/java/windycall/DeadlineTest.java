package windycall;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void standardDateFormatTest() {
        String description = "eat lunch";
        String date = "2023-01-26";
        Deadline deadlineTask = new Deadline(description, false, date);
        String expectedFormat = "2023-01-26";
        assertEquals(expectedFormat, Parser.processDate(expectedFormat).toString());
    }

    @Test
    public void anotherDateFormatTest() {
        String description = "eat lunch";
        String date = "26/1/2023";
        Deadline deadlineTask = new Deadline(description, false, date);
        String expectedFormat = "2023-01-26";
        assertEquals(expectedFormat, Parser.processDate(expectedFormat).toString());
    }
}
