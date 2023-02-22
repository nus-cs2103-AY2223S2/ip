import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import storage.Storage;
import task.Event;
import task.TaskList;
import task.Todo;

public class StorageTest {
    @Test
    public void saveLoad() {
        Storage storage = new Storage("./data/.gradle/");
        TaskList tasks = new TaskList();
        tasks.add(new Event("Body", LocalDateTime.of(2023, 3, 4, 11, 35), LocalDateTime.of(2023, 3, 4, 14, 20)));
        tasks.get(0).mark();
        tasks.add(new Todo("Item"));
        assertDoesNotThrow(() -> storage.save("saveLoad", tasks));
        tasks.clear();
        assertDoesNotThrow(() -> storage.load("saveLoad", tasks));
        assertEquals("[E][X] Body (04 Mar 2023 11:35 AM - 04 Mar 2023 02:20 PM)", tasks.get(0).toString());
        assertEquals("[T][ ] Item", tasks.get(1).toString());
    }

    @Test
    public void saveLoad_empty() {
        Storage storage = new Storage("./data/.gradle/");
        TaskList tasks = new TaskList();
        assertDoesNotThrow(() -> storage.save("saveLoad_empty", tasks));
        tasks.add(new Todo("Item"));
        assertDoesNotThrow(() -> storage.load("saveLoad_empty", tasks));
        assertEquals(0, tasks.size());
    }

    @Test
    public void load_noFile_exceptionThrown() {
        Storage storage = new Storage("./data/.gradle/");
        TaskList tasks = new TaskList();
        IOException ex = assertThrows(IOException.class, () ->
                storage.load("load_noFile_exceptionThrown", new TaskList()));
        assertEquals(".\\data\\.gradle\\load_noFile_exceptionThrown", ex.getMessage());
    }
}
