package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    @Test
    void eventTestUi(){
        Event event = new Event("read book", "2022-01-22", "2022-01-23", false);
        assertEquals("\t[E][ ] read book (from: 2022-01-22 to: 2022-01-23)", event.toString());
    }

}