package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


/**
 * Represents a duke.Storage object that stores the tasks in the hard disk.
 */
public class Storage {
    private final File file;
    private final ArrayList<Task> tasks;

    /**
     * Creates a duke.Storage object.
     *
     * @param filePath the path of the file to be stored.
     */
    public Storage(String filePath) {
        if (filePath == null) {
            throw new IllegalArgumentException("File path cannot be null");
        }
        if (filePath.isBlank()) {
            throw new IllegalArgumentException("File path cannot be blank");
        }

        this.file = new File(filePath);
        if (!this.file.exists()) {
            try {
                this.file.getParentFile().mkdirs();
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.tasks = new ArrayList<>();
    }

    /**
     * Loads the tasks from the hard disk.
     *
     * @return the list of tasks.
     * @throws DukeException if the file is not found.
     */
    public TaskList load() throws DukeException {
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) {
                String storageString = sc.nextLine();
                Task task = parseStorageString(storageString);
                this.tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found");
        }
        return new TaskList(this.tasks);
    }

    /**
     * Parses the storage string and returns the duke.task.Task object.
     *
     * @param storageString the string representation of the task to be stored in the hard disk.
     * @return the duke.task.Task object.
     * @throws DukeException if the storage string is invalid.
     */
    private Task parseStorageString(String storageString) throws DukeException {
        String[] split = storageString.split(" \\| ");
        if (split.length < 3) {
            throw new DukeException("Invalid storage string: " + storageString);
        }
        Task task;
        switch (split[0]) {
        case "T":
            task = new ToDo(split[2]);
            break;
        case "D":
            if (split.length < 4) {
                throw new DukeException("Invalid storage string: " + storageString);
            }
            task = new Deadline(split[2], split[3]);
            break;
        case "E":
            if (split.length < 5) {
                throw new DukeException("Invalid storage string: " + storageString);
            }
            task = new Event(split[2], split[3], split[4]);
            break;
        default:
            throw new DukeException("Invalid storage string: " + storageString);
        }
        if (split[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Saves the tasks to the hard disk.
     *
     * @param tasks the list of tasks.
     * @throws DukeException if the file is not found.
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(tasks.toStorageString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("File not found");
        }
    }
}
