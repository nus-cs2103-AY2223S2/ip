import duke.Storage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class StorageTest {
    @Test
    public void testStorageLoad() {
        Storage storage = new Storage();
        Assertions.assertNotNull(storage.load());
    }
}
