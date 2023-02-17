
package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toFileFormatTest(){
        Deadline deadline = new Deadline("return book", "2020-05-12 1800");
        assertEquals("D | 0 | return book | 2020-05-12 1800\n", deadline.formatForFile());
    }

    @Test
    public void toStringTest() {
        Deadline deadline = new Deadline("test", "2020-05-12 1800");
        assertEquals("[D][ ] test (by: May 12 2020 18:00)", deadline.toString());
    }
}