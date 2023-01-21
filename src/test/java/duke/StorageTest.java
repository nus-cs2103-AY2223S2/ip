package duke;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    @Test
    public void fileCreationTest() throws IOException {
        String fp = "fileCreationTest.txt";
        File f = new File(fp);
        new Storage(fp);
        assertTrue(f.exists());
    }

}
