package TaskTests;

import duke.exceptions.DukeExceptions;
import duke.tasks.Event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void inputTest() {
        String input = "event abc /from 2023-02-15 1800 /to 2023-02-26 1800";
        Event event = new Event();
        try {
            event.genDscp(input);
            assertEquals("abc (from: 2023-02-15 1800 to: 2023-02-26 1800", event.getDescription());
        } catch(DukeExceptions e){
        }
    }

    @Test
    public void invalidFromToTest() {
        String input = "event abc /from 1 /to 2";
        Event event = new Event();
        try {
            event.genDscp(input);
        } catch(DukeExceptions e){
            assertEquals("Please enter the event period correctly", e.getMessage());
        }
    }
}
