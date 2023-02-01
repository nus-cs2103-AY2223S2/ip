package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import duke.commands.ByeCommand;
import duke.exceptions.EmptyCommandException;
import duke.exceptions.InvalidCmdValueException;
import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidTaskTypeException;
import duke.exceptions.InvalidTimeException;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.utils.Parser;
import duke.utils.Storage;



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
