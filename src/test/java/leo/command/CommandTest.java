package leo.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import leo.storage.Storage;

public class CommandTest {

    @Test
    public void extractTaskNumTest() throws Exception {
        Storage storage = new Storage("ip/data/leo.txt");
        String str = "delete 5";
        Command command = new Command(storage, str);
        assertEquals(5, command.extractTaskNum());
    }
}
