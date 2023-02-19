package berry.storage;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import berry.exception.IllegalValueException;

public class StorageTest {

    public static final String TEST_DATA_FOLDER = "test/data/StorageTest";

    @Test
    public void constructor_nullFilePath_exceptionThrown() throws Exception {
        assertThrows(NullPointerException.class, () -> new Storage(null));
    }

    @Test
    public void constructor_noTxtExtension_exceptionThrown() throws Exception {
        assertThrows(IllegalValueException.class, () ->
                new Storage(TEST_DATA_FOLDER + "/" + "InvalidFileName"));
    }

    @Test
    public void load_invalidFormat_exceptionThrown() throws Exception {
        // The file contains valid txt data, but does not match the Person format
        Storage storage = new Storage(TEST_DATA_FOLDER + "/" + "InvalidData.txt");
        assertThrows(FileNotFoundException.class, () -> storage.load());
    }
}
