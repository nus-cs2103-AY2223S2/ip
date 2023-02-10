package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import duke.exceptions.IOException;
import java.nio.file.Path;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents a storage that stores the tasks in a file.
 * 
 * @author Samarth Verma
 */
public class Storage {
    private Path path;
    private File f;
    private boolean isInitialized = false;

    /**
     * Creates a new Storage object.
     * 
     * @param path The path of the file.
     */
    public Storage(String path) {
        this.path = Path.of(path);
        this.f = this.path.toFile();
    }

    public String getPath() {
        return path.toString();
    }

    private void initialize() throws IOException {
        if (isInitialized) {
            return;
        }
        if (!f.exists()) {
            createFile();
        }
        isInitialized = true;
    }

    /**
     * Creates a new file.
     * 
     * @throws IOException
     */
    private void createFile() throws IOException {
        try {
            f.getParentFile().mkdirs();
            f.createNewFile();
        } catch (Exception e) {
            throw new IOException("Unable to create file");
        }
    }

    public void save(TaskList list) throws IOException {
        this.initialize();
        StringBuilder sb = new StringBuilder();
        for (Task task : list) {
            sb.append(task.serialize());
            sb.append(System.lineSeparator());
        }

        try {
            FileWriter fw = new FileWriter(f);
            fw.write(sb.toString());
            fw.close();
        } catch (Exception e) {
            throw new IOException("Unable to write to file");
        }

    }

    public TaskList read() throws IOException {
        this.initialize();
        TaskList list = new TaskList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;

            while ((line = br.readLine()) != null) {
                Task task = Task.deserialize(line);
                list.add(task);
            }
            br.close();
        } catch (Exception e) {
            throw new IOException("Unable to read from file");
        }
        return list;

    }
}
