package duke;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.util.Collections;

class StorageTest {

    @TempDir
    Path tempDir;

    @Test
    void write_string_success() throws IOException {
        Path path = tempDir.resolve("test.txt");
        Storage s = new Storage(path.toString());
        s.write("[T][ ] Buy groceries");

        assertArrayEquals(Collections.singletonList("[T][ ] Buy groceries").toArray(), Files.readAllLines(path).toArray());
    }
}
