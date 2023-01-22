package seedu.duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest() throws IOException, DukeException {
        Duke duke = new Duke("duke.txt");
        duke.run();
        assertEquals(2, 2);
    }
}
