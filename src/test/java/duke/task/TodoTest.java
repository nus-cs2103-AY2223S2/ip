package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {


    @Test
    void getFileDesc() {
        Todo test = new Todo("Test");
        assertEquals("T|0|Test", test.getFileDesc());
        test.mark();
        assertEquals("T|1|Test", test.getFileDesc());
    }

    @Test
    void testToString() {
        Todo test = new Todo("Test");
        assertEquals("[T][ ] Test", test.toString());
        test.mark();
        assertEquals("[T][X] Test", test.toString());
    }
}