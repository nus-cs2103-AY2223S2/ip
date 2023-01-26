import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import duke.*;
import org.testng.Assert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class DukeTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void uiWelcomeTest() {
        Ui ui = new Ui();
        ui.showWelcome();

        assertEquals("Hi! I'm Samantha\n" + "    How can I help?", outputStreamCaptor.toString().trim());
    }
    @Test
    void todoSaveTest() {
        Todo todo = new Todo("go home");
        System.out.println(todo.toString());

        assertEquals("[T][ ] go home", outputStreamCaptor.toString().trim());
    }
    @Test
    void deadlineSaveTest() {
        Deadline deadline = new Deadline("finish work", "29-01-2023");
        System.out.println(deadline.toString());

        assertEquals("[D][ ] finish work (by: 29-01-2023)", outputStreamCaptor.toString().trim());
    }


}

