package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

class EventTest {
    @Test
    void eventTestUi() throws DukeException {
        Event event = new Event("school", "2022-02-23", "2022-03-23", false);
        assertEquals("\t[E][ ] school (from: Feb 23 2022 to: Mar 23 2022)", event.toString());
    }

}
