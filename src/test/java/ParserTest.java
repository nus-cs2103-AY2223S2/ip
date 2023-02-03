import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import page.PageException;
import page.Parser;
import page.QuestLog;
import page.Ui;

public class ParserTest {
    @Test
    public void parseExecute_todoNoTask_exceptionThrown() {
        Parser parser = new Parser();
        QuestLog questLog = new QuestLog();
        Ui ui = new Ui();
        try {
            parser.parseExecute("todo", ui, null, questLog);
            fail();
        } catch (PageException e) {
            assertEquals("Sorry, please include a task description!", e.getMessage());
        }
    }
}
