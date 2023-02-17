package duke;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DukeTest {
    private Duke duke;

    private final InputStream in = System.in;
    private final PrintStream out = System.out;

    @BeforeEach
    public void setUp() {
        duke = new Duke();
    }

    @AfterEach
    public void resetIO() {
        System.setIn(in);
        System.setOut(out);
    }

    @Test
    @DisplayName("Test to make sure that Duke object is initialised properly")
    public void createDukeTest() {
        assertNotNull(duke, "Duke object should be properly initialised.");
    }
}
