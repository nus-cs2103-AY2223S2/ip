import Duke.Chatbot.Chatbot;
import Duke.Chatbot.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DukeTest {
    final static String FILE_NAME = "save.txt";
    final static String SAVE_DIRECTORY = "data";

    @Test
    public void chatBotDummyTest() {
        Chatbot chatbot = new Chatbot(SAVE_DIRECTORY, FILE_NAME);
        assertTrue(chatbot.isActive);
    }

    @Test
    public void TaskListDummyTest() {
        TaskList tasks = new TaskList();

        assertEquals(tasks.numTasks(), 0);
    }
}