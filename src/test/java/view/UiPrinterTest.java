package view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import membot.view.UiPrinter;

public class UiPrinterTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void printlnEmptyTest() {
        UiPrinter p = new UiPrinter(System.out::println);

        String[] s1 = {};

        p.println(s1);
        assertEquals(
                "",
                this.outputStreamCaptor.toString().trim()
        );
    }

    @Test
    public void printlnTest() {
        UiPrinter p = new UiPrinter(m -> System.out.println(m.getMessage()));

        String[] s1 = {"test", "1", "2", "3"};

        p.println(s1);
        assertEquals(
                String.join("\n", s1),
                this.outputStreamCaptor.toString().trim()
        );
    }

    @Test
    public void printlnErrorEmptyTest() {
        UiPrinter p = new UiPrinter(System.out::println);

        String[] s1 = {};

        p.printlnError(s1);
        assertEquals(
                "",
                this.outputStreamCaptor.toString().trim()
        );
    }

    @Test
    public void printlnErrorTest() {
        UiPrinter p = new UiPrinter(m -> System.out.println(m.getMessage()));

        String[] s1 = {"testerror", "1", "2", "3"};

        p.printlnError(s1);
        assertEquals(
                String.join("\n", s1),
                this.outputStreamCaptor.toString().trim()
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
