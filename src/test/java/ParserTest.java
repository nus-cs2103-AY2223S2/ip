import duke.Parser;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ParserTest {
    Stage stage = new Stage();
    Parser parser = new Parser(stage);
    @Test
    public void testParseByeCommand(){
        Assertions.assertEquals("Thanks for using DUKE MK-II. See you soon!\n",parser.parseCommandWithResponse("bye"));
    }

    @Test
    public void testParseListCommand(){

    }
}
