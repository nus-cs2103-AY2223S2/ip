package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.constant.Message;
import duke.ui.Ui;

public class DukeTest {

    private final ByteArrayInputStream  inDukeContent = new ByteArrayInputStream("bye".getBytes());
    private final ByteArrayOutputStream outDukeContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream outUiContent = new ByteArrayOutputStream();
    Duke duke;
    Ui ui;

    @BeforeEach
    void setUp() {
        duke = new Duke(inDukeContent, new PrintStream(outDukeContent));
        ui = new Ui(System.in, new PrintStream(outUiContent));
    }

    @Test
    void testRun() {
        duke.run();
        
        ui.showWelcome();
        ui.showLine();
        ui.println(Message.BYE);
        ui.showLine();
        assertEquals(outUiContent.toString(), outDukeContent.toString());
    }

}
