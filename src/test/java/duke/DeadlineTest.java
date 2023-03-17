package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void summaryTest(){
        Deadline test = new Deadline("Test", true, "2010-10-19");
        assertEquals("D___✓___Test___2010-10-19", test.summary());
    }

    @Test
    public void dateTest(){
        Deadline test = new Deadline("Test", true, "2010-10-19");
        assertEquals("[ D ][ ✓ ] Test(Oct 19 2010)", test.toString());
    }

}
