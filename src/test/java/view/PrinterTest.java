package view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrinterTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void listPrintTest(){
        String[] list1 = {"Task 1", "Task 2", "Task 3"};
        Printer.listPrint(list1);

        assertEquals("\t1. Task 1\n\t2. Task 2\n\t3. Task 3\n====================",
                outputStreamCaptor.toString().stripTrailing());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
