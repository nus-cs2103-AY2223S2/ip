package leo.command;

import leo.storage.Storage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {

    @Test
    public void extractTaskNumTest() throws Exception {
        Storage storage = new Storage("ip/data/leo.txt");
        String str = "delete 5";
        Command command = new Command(storage, str);
        assertEquals(5, command.extractTaskNum());
    }
}
