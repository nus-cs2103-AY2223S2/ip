package chungus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class StorageTest {
    private static final Todo SAMPLE_TODO = new Todo("eliminate the working class");
    private static final Deadline SAMPLE_DEADLINE = new Deadline("eliminate the middle class", LocalDateTime.now());
    private static final Event SAMPLE_EVENT = new Event("eliminate the top 1%", LocalDateTime.now(),
            LocalDateTime.now().plusDays(2));

    @Test
    public void canReadAndWrite() throws IOException {
        // Write a bunch of tasks to a temporary file, then read them out. Check that
        // the same tasks are retrieved.
        TaskList tasks = new TaskList();
        tasks.add(SAMPLE_TODO);
        tasks.add(SAMPLE_DEADLINE);
        tasks.add(SAMPLE_EVENT);

        File tmpFile = File.createTempFile("Chungus-", ".tmp.db");
        Storage st = new Storage(tmpFile);

        st.write(tasks);

        TaskList sameTasks = st.read();
        assertEquals(tasks, sameTasks);
    }
}
