package duke.bot;

import duke.taskmanager.Tasks;
import duke.taskmanager.ToDo;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void StorageTest1() {
        try {
            Tasks td = new ToDo("borrow book");
            Storage s = new Storage("duke/bot/data/tasks.txt");
            Tasks tl = s.load().get(0);
            assertEquals(td.getDesc(), tl.getDesc());
        } catch(Exception ignored) {

        }

    }
}
