package duke;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DukeTest {
    private Duke duke;

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        duke = new Duke("data/tasks.txt");
    }
    
    @AfterEach
    public void resetIO() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Ensure duke object is created")
    public void testCreateDukeObject() {
        assertNotNull(duke, "Duke object should be created");
    }
}
