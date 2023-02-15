package colette.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        Deadline testDeadline = new Deadline("return library books", "2022-02-02");
        assertEquals("[D] [ ] return library books (by: Feb 2 2022)", testDeadline.toString());
        testDeadline.setDone(true);
        assertEquals("[D] [X] return library books (by: Feb 2 2022)", testDeadline.toString());
    }

    @Test
    public void testFileRepresentationConversion() {
        Deadline testDeadline = new Deadline("return library books", "2022-02-02");
        assertEquals("D@0@return library books@2022-02-02", testDeadline.getFileRepresentation());
        testDeadline.setDone(true);
        assertEquals("D@1@return library books@2022-02-02", testDeadline.getFileRepresentation());
    }

    @Test
    public void deadlineCreation_invalidDate_exceptionThrown() {
        try {
            assertEquals("[D] [ ] return library books (by: Feb 2 2022)",
                new Deadline("return library books", "2022-2-2").toString());
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Text '2022-2-2' could not be parsed at index 5", e.getMessage());
        }
    }
}
