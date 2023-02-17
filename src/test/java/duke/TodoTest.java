package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void summaryTest(){
        Todo test = new Todo("Test", true);
        assertEquals("T___✓___Test", test.summary());
    }

}