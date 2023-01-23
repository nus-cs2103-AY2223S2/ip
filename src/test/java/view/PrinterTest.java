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
    public void listPrintTest() {
        String[] list1 = {"Task 1", "Task 2", "Task 3"};
        Printer.listPrint(list1);
        assertEquals("\t1. Task 1\n\t2. Task 2\n\t3. Task 3\n====================",
                outputStreamCaptor.toString().stripTrailing());
        outputStreamCaptor.reset();

        String[] list2 = {"This is a very long task"};
        Printer.listPrint(list2);
        assertEquals("\t1. This is a very long task\n====================",
                outputStreamCaptor.toString().stripTrailing());
        outputStreamCaptor.reset();

        String[] list3 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        Printer.listPrint(list3);
        assertEquals("\t1. 1\n\t2. 2\n\t3. 3\n\t4. 4\n\t5. 5\n\t6. 6\n\t7. 7\n\t8. 8\n\t9. 9\n\t10. 10" +
                        "\n====================", outputStreamCaptor.toString().stripTrailing());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
