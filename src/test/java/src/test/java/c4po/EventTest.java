package src.test.java.c4po;
import org.junit.jupiter.api.Test;
import src.main.c4po.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void getTaskInlineFormatTest(){
        Event newEvent = new Event("dummydesc", "1pm", "3pm", 0);

        String expected = "1. [E][ ] dummydesc (from: 1pm to: 3pm)";
        assertEquals(expected, newEvent.getTaskInline(1));
    }
}
