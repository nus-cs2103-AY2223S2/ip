package duke.tasks;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    @Test
    void eventTestUi() throws DukeException {
        Event event = new Event("school", "2022-02-23", "2022-03-23", false);
        assertEquals("\t[E][ ] school (from: Feb 23 2022 to: Mar 23 2022)", event.toString());
    }

}