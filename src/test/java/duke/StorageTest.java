package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    Storage storage = new Storage("data/tasks.txt");

    @Test
    public void isLoadError() {
        // A unit test for Storage#isLoadError

        // automatically verify the response
        assertFalse(storage.isLoadError());
    }

    @Test
    public void setLoadError() {
        // A unit test for Storage#setLoadError
        // test setup
        storage.setLoadError(true);

        // automatically verify the response
        assertTrue(storage.isLoadError());
    }
}
