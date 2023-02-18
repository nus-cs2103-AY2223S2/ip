package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Pattern;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a storage object for backup.
 */
public class Storage {
    private final Path backupPath;

    /**
     * Returns a storage object.
     *
     * @param filepath path of backup file.
     */
    public Storage(String filepath) {
        String home = System.getProperty("user.dir");
        Path path = java.nio.file.Paths.get(home, "src", "main", "data");
        boolean directoryExists = java.nio.file.Files.exists(path);

        if (!directoryExists) {
            boolean createDirSuccess = new File(path.toUri()).mkdirs();
            if (!createDirSuccess) {
                System.out.println("Could not create /data directory.");
            }
        }
        this.backupPath = Path.of(path + "/" + filepath);
    }

    /**
     * Load entire task list into the backup file.
     *
     * @param arr task list.
     */
    public void writeArray(TaskList arr) {
        File file = new File(backupPath.toString());
        try {
            boolean fileCreated = file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, false);
            for (int i = 0; i < arr.getLength(); i++) {
                fileWriter.write(arr.getTask(i).toBackup());
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads backup file and converts into task list.
     *
     * @return task list.
     */
    public TaskList readArray() {
        TaskList arr = new TaskList();

        try {
            File file = new File(backupPath.toString());
            System.out.println(backupPath);

            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String[] line = myReader.nextLine().split("\\|");

                Task task = null;
                line[0] = line[0].strip();
                assert Pattern.compile("[TDE]").matcher(line[0]).matches() : "should be one of 3 types";

                switch (line[0]) {
                case "T":
                    task = new Todo(line[2]);
                    break;
                case "D":
                    task = new Deadline(line[2], line[3]);
                    break;
                case "E":
                    task = new Event(line[2], line[3], line[4]);
                    break;
                default:
                    break;
                }

                if (line[1].strip().equals("1")) {
                    assert task != null;
                    task.setStatus(true);
                }
                arr.addTask(task);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the backup file");
            e.printStackTrace();
        }

        return arr;
    }
}
