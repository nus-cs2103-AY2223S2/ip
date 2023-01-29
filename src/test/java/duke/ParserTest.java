package duke;

import duke.commands.ByeCommand;
import duke.exceptions.*;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.utils.Parser;
import duke.utils.Storage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private static Parser parser;
    private static Storage storage;
    private static Ui ui;
    private static TaskList taskList;
    private final File savedFile = new File("savedFile.txt");

    @BeforeAll
    public static void setup() {
        parser = new Parser();
        ui = new Ui();
        storage = new Storage();
        storage.loadFromFile(new File("savedFile.txt"));
        taskList = new TaskList();
        taskList.setTaskList(storage.getStorage());
    }

    @Test
    public void parseTest() throws EmptyCommandException, InvalidTimeException,
            InvalidDateException, InvalidTaskTypeException, InvalidCmdValueException {
        String bye = "bye";
        assertEquals(parser.parse(bye, taskList, storage, ui, savedFile).getClass(),
                ByeCommand.class);
    }

}
