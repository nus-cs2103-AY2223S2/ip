package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void addTest() {
        assertEquals("[E][ ] DEVCON (from: Monday to: Tuesday)", new Event("DEVCON", "Monday", "Tuesday").toString());
    }

    @Test
    public void addTest2() {
        assertEquals("[E][X] UNICON (from: Monday to: Tuesday)", new Event("UNICON", "Monday", "Tuesday", true).toString());
    }

}