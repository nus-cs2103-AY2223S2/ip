package chungus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

/**
 * Helps manage storage of tasks to disk.
 */
class Storage {
    private File file;

    /**
     * Constructor for Storage.
     * 
     * @param file The file to use for reading and writing of tasks.
     */
    public Storage(File file) {
        this.file = file;
    }

    /**
     * Reads tasks from disk.
     * 
     * @return A list of tasks.
     * @throws IOException When reading the file fails.
     */
    public TaskList read() throws IOException {
        return new TaskList(Files.readAllLines(file.toPath())
                .stream()
                .map(line -> Task.unmarshal(line))
                .collect(Collectors.toList()));
    }

    /**
     * Writes a list of task to disk. This overrides the old contents of the file,
     * so make sure the full list is provided each time.
     * 
     * @param tasks A list of tasks.
     * @throws RuntimeException When writing to disk fails.
     */
    public void write(TaskList tasks) {
        try {
            Files.write(file.toPath(), tasks.marshal().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save tasks to file", e);
        }
    }
}
