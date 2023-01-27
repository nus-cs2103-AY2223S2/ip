package leo.storage;

import leo.leoException.IncorrectMarkException;
import leo.leoException.NoStorageFileException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void filePathRetrieval_success() throws NoStorageFileException, IncorrectMarkException {
        Storage storage = new Storage("data/leo.txt");
        assertEquals("/Users/jamieeeleow/Documents/cs2103t/iP/ip/data/leo.txt", storage.getDataFilePath());
    }

    @Test
    public void testDataLength_success() throws NoStorageFileException, IncorrectMarkException {
        Storage storage = new Storage("data/leo.txt");
        assertEquals(5, storage.getDataLength());
    }
}
