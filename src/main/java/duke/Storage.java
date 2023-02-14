package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Manages storage of tasks between sessions.
 */
public class Storage {
    private File storageFile;

    /**
     * Constructor for a Storage object.
     * @param file File path for storage.
     */
    public Storage(String file) {
        try {
            String innerPath = System.getProperty("user.dir");
            Path path = Paths.get(innerPath, "src", "main", "java", "data");
            Files.createDirectories(path);
            storageFile = new File(path.toString() + file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses Storage file for previously stored Tasks.
     * @return ArrayList containing all previously stored Tasks.
     */
    public ArrayList<Task> load() {
        try {
            ArrayList<Task> tasks = new ArrayList<Task>();
            if (!storageFile.createNewFile()) {
                // Parse the file and add the tasks
                Scanner s = new Scanner(storageFile);
                while (s.hasNext()) {
                    String line = s.nextLine();
                    switch (line.charAt(0)) {
                    case 'T':
                        tasks.add(Todo.fromSaveFormat(line));
                        break;
                    case 'D':
                        tasks.add(Deadline.fromSaveFormat(line));
                        break;
                    case 'E':
                        tasks.add(Event.fromSaveFormat(line));
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + line);
                    }
                }
            }
            return tasks;
        } catch (IOException e) {
            System.out.println("Error creating Storage file");
            return new ArrayList<Task>();
        }
    }

    /**
     * Adds Task to storage file.
     * @param t Task object to be stored.
     */
    public void add(Task t) {
        try {
            FileWriter fw = new FileWriter(storageFile, true);
            if (storageFile.length() == 0) {
                fw.write(t.toSaveFormat());
            } else {
                fw.write(System.lineSeparator() + t.toSaveFormat());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Updates storage file with the current state of all tasks.
     * @param tasks TaskList containing all current tasks.
     */
    public void saveState(TaskList tasks) {
        try {
            int numberOfTasks = tasks.size();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numberOfTasks; i++) {
                Task t = tasks.getTask(i);
                if (i != 0) {
                    sb.append(System.lineSeparator());
                }
                sb.append(t.toSaveFormat());
            }
            FileWriter fw = new FileWriter(storageFile);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }
}
