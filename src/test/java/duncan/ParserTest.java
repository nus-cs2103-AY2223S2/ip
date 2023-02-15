package duncan;

import command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParserTest {

    @Test
    public void isExitTest() {
        try {
            Ui ui = new Ui();
            Parser parser = new Parser(new DuncanList(ui), new DuncanList(ui), new Storage("", "", ui), ui);
            Command c = parser.parse("todo false");

            assertFalse(c.isExit());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
