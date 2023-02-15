package commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import membot.Membot;
import membot.commands.Command;
import membot.view.UiPrinter;

public class ListCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private UiPrinter p;
    private Membot m;


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        p = new UiPrinter(m -> System.out.println(m.getMessage()));
        m = new Membot(p, true);

        assertDoesNotThrow(this.outputStreamCaptor::reset);
    }

    @Test
    public void executeTest() {
        assertDoesNotThrow(() -> {
            Command byeCommand = Command.parse("list", p, m);
            byeCommand.execute();

            assertEquals(
                    "Excellent! You do not have any tasks at hand!",
                    this.outputStreamCaptor.toString().trim()
            );
        });
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
