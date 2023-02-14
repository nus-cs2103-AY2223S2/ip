package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TodoTask;

/**
 * The Storage class is responsible for loading tasks from the file and saving tasks in the file.
 * @author Junyi
 */
public class Storage {

    /* The file where data will be stored */
    private final File storageFile;

    /**
     * Constructor for Storage.
     * @param path File path for the data to be stored.
     */
    public Storage(String path) {
        storageFile = new File(path);
    }

    /**
     * Saves the task data on to local storage.
     * @param taskList TaskList of Duke's tasks.
     * @throws DukeException thrown if files/directories failed to create.
     * @throws IOException thrown if file access error.
     */
    public void save(TaskList taskList) throws DukeException, IOException {
        if (!storageFile.getParentFile().exists()) {
            if (!storageFile.getParentFile().mkdirs()) {
                throw new DukeException("Arii can't create the directories. Is your system faulty?");
            }
        }

        if (!storageFile.exists()) {
            if (!storageFile.createNewFile()) {
                throw new DukeException("Arii can't create the data file. Is your system faulty?");
            }
        }

        FileWriter fw = new FileWriter(storageFile);
        for (Task task : taskList.allTasks()) {
            fw.write(task.serialise());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Returns an array of tasks loaded from local storage.
     * @return Array of Task.
     */
    public Task[] load() throws DukeException {
        if (!storageFile.exists()) {
            return new Task[0];
        }

        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner s = new Scanner(storageFile);
            while (s.hasNext()) {
                String data = s.next();
                String identifier = data.split(",")[0];
                Task task;

                switch (identifier) {
                case "TT":
                    task = TodoTask.deserialise(data);
                    break;
                case "ET":
                    task = EventTask.deserialise(data);
                    break;
                case "DT":
                    task = DeadlineTask.deserialise(data);
                    break;
                case "T":
                default:
                    task = Task.deserialise(data);
                    break;
                }
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Fail to load from data file...");
        }

        return taskList.toArray(new Task[0]);
    }

}
