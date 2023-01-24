package duke;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {

    private static final Path PATH_TO_FILE = Path.of(".data", "tasklist.txt");

    public Storage() {}

    private boolean createFileIfNotExists() {
        File file = PATH_TO_FILE.toFile();
        if (file.exists()) {
            return false;
        }
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException ex) {
        }
        return true;
    }

    public TaskList readTaskList() {
        if (createFileIfNotExists()) {
            return new TaskList();
        }
        try (InputStream in = Files.newInputStream(PATH_TO_FILE);
                ObjectInputStream objIn = new ObjectInputStream(in)) {
            return (TaskList) objIn.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            // should continue execution by returning an empty TaskList
            UI.echo("Something goes wrong with saved log. A new log will be created...");
            return new TaskList();
        }
    }

    public void writeTaskList(TaskList list) {
        createFileIfNotExists();
        try (OutputStream out = Files.newOutputStream(PATH_TO_FILE);
                ObjectOutputStream objOut = new ObjectOutputStream(out)) {
            objOut.writeObject(list);
        } catch (IOException ex) {
            throw new DukeException(ex);
        }
    }
}
