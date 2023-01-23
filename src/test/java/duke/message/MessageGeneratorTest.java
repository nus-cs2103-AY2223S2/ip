package duke.message;

import duke.storage.Storage;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageGeneratorTest {

    @Test
    public void generateListMessage_emptyListInput_emptyListString()  {
        MessageGenerator messageGenerator = new MessageGenerator(new TaskList(new ArrayList<>()), new Storage());
        assertEquals("Here are the tasks in your list:\n", messageGenerator.generateListMessage());
    }
}
