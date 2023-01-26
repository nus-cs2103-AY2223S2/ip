package duke.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Class containing methods related to loading and saving tasks to and from the disk
 */
public class Storage {
    public static List<Task> loadFromDisk(String path) throws IOException, ClassNotFoundException {
        if (Files.exists(Paths.get(path)))  {
            try (
                final FileInputStream ifstream = new FileInputStream(path);
                final ObjectInputStream objStream = new ObjectInputStream(ifstream);
            ) {
                @SuppressWarnings("unchecked")
                List<Task> tasks = (List<Task>) objStream.readObject();
                return tasks;
            }
        }

        return new ArrayList<>();
    }

    public static void saveToDisk(String path, List<Task> tasks) throws IOException {
        try (
            final FileOutputStream ofstream = new FileOutputStream(path);
            final ObjectOutputStream objStream = new ObjectOutputStream(ofstream);
        ) {
            objStream.writeObject(tasks);
        }
    }
}
