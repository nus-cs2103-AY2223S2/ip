package leo.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import leo.leoexception.IncorrectMarkException;
import leo.leoexception.NoStorageFileException;

public class StorageTest {

    @Test
    public void filePathRetrieval_success() throws NoStorageFileException, IncorrectMarkException {
        Storage storage = new Storage("data/leo.txt");
        assertEquals("/Users/jamieeeleow/Documents/cs2103t/iP/ip/data/leo.txt", storage.getDataFilePath());
    }

    @Test
    public void testDataLength_success() throws NoStorageFileException, IncorrectMarkException {
        Storage storage = new Storage("data/leo.txt");
        assertEquals(7, storage.getDataLength());
    }
}
