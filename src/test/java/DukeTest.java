import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import duke.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DukeTest {

    private void print(String output) {
        System.out.println(output);
    }

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void isDeleteTest1(){
        assertEquals(true, duke.Parser.is_Delete("delete 1"));
    }

    @Test
    public void isDeleteTest2(){
        assertEquals(false, duke.Parser.is_Delete("delete"));
    }

    @Test
    public void strToBoolTest1(){
        assertEquals(true, duke.Storage.strToBool("1"));
    }

    @Test
    public void strToBoolTest2(){
        assertEquals(false, duke.Storage.strToBool("0"));
    }

    @Test
    void givenSystemOutRedirection_whenInvokePrintln_thenOutputCaptorSuccess() {
        print("Hello Readers!");

        assertEquals("Hello Readers!", outputStreamCaptor.toString()
                .trim());
    }

    String line = "      _____________________________________________________________________";

    @Test
    void displayClearTest1() {
        Ui.displayClear();

        assertEquals("Got it. I have cleared the task-list.\n" + line, outputStreamCaptor.toString()
                .trim());
    }

}