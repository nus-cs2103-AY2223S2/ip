package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * A utility class to save Task objects in the hard disk and persist them
 * for the subsequent sessions.
 */
public class Storage {
    private static final String BACKUP_FILE_NAME = "duke.txt";
    private static final String BACKUP_FILE_DIR = "data";
    /**
     * Recreates a Task object from the disk format.
     * @param str A string that represents a Task in disk format.
     * @return The corresponding Task object.
     */
    private static Task fromString(String str) {
        Task newtask;
        String[] tokens = str.split("\\|");
        if (tokens[0].equals("T")) {
            newtask = new ToDo(tokens[2], tokens[1].equals("1"));
        } else if (tokens[0].equals("D")) {
            newtask = new Deadline(tokens[2], tokens[3], tokens[1].equals("1"));
        } else {
            newtask = new Event(tokens[2], tokens[3], tokens[4], tokens[1].equals("1"));
        }
        return newtask;
    }

    /**
     * Recreates the list of Task objects from a file in the correct format.
     * @return List of Task objects that was stored in the file.
     * @see <a href="https://www.sghill.net/2014/how-do-i-make-cross-platform-file-paths-in-java/">Tutorial 1</a>
     * @see <a href="https://www.baeldung.com/java-path-vs-file">Tutorial 2</a>
     * @see <a href="https://www.digitalocean.com/community/tutorials/java-read-file-line-by-line">Tutorial 3</a>
     */
    public List<Task> loadData() {
        List<Task> result = new ArrayList<>();
        try {
            // Solution below adapted from
            // https://www.sghill.net/2014/how-do-i-make-cross-platform-file-paths-in-java/
            Path path = Paths.get(System.getProperty("user.dir"), BACKUP_FILE_DIR, BACKUP_FILE_NAME);
            if (!Files.exists(path)) {
                Files.createDirectories(Paths.get(System.getProperty("user.dir"), BACKUP_FILE_DIR));
                Files.createFile(path);
            }
            BufferedReader bf = Files.newBufferedReader(path);
            String line = bf.readLine();
            while (line != null) {
                result.add(fromString(line));
                line = bf.readLine();
            }
            bf.close();
        } catch (IOException e) {
            result = new ArrayList<>();
            System.out.println("Unable to open " + BACKUP_FILE_DIR + " \\ " + BACKUP_FILE_NAME);
        }
        return result;
    }
    /**
     * Saves the list of Task objects to a file.
     * @param userTasks List of Task objects to save.
     */
    public void saveData(List<Task> userTasks) {
        Path path = Paths.get(System.getProperty("user.dir"), BACKUP_FILE_DIR, BACKUP_FILE_NAME);
        BufferedWriter bw = null;
        // Solution below adapted from
        // https://beginnersbook.com/2014/01/how-to-write-to-file-in-java-using-bufferedwriter/
        try {
            bw = Files.newBufferedWriter(path);
            for (Task t: userTasks) {
                bw.write(t.toDiskFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.out.println("Unable to save to " + BACKUP_FILE_DIR + " \\ " + BACKUP_FILE_NAME);
            }
        }
    }
}
