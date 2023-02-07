package duke.storage;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Storage handles all the interaction with the memory.
 */

public class Storage {
    private String directory;
    private String path;

    /**
     * Initialises the storage file.
     *
     * @param d directory of the storage file.
     * @param p Path of the storage file.
     */
    public Storage(String d, String p) {
        this.directory = d;
        this.path = p;
    }

    /**
     * Save the file from the current session onto the file.
     *
     * @param taskList TaskList for the current session.
     * @throws IOException If the tasks cannot be saved to file.
     */
    public void saveTasks(TaskList taskList) throws IOException {
        assert taskList != null;

        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File dataFile = new File(path);
        dataFile.createNewFile();

        FileWriter myWriter = new FileWriter(path);
        boolean isFirst = true;
        for (Task t : taskList.getTasks()) {
            if (!isFirst) {
                myWriter.write("\n");
            }
            myWriter.write(t.toSaveString());
            isFirst = false;
        }
        myWriter.close();
    }

    /**
     * Loads the tasks from the file onto the tasks list.
     *
     * @param taskList TaskList for the current session.
     * @throws IOException If storage file/directory cannot be created.
     * @throws DukeException If storage file is in the wrong format.
     */
    public void loadTasks(TaskList taskList) throws IOException, DukeException {
        assert taskList != null;

        Scanner fileReader = new Scanner(new File(path));
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            String[] taskData = data.split("\\$\\$\\$");

            switch (taskData[0]) {
            case "T":
                taskList.getTasks().add(new Todo(taskData[1]));
                break;
            case "D":
                taskList.getTasks().add(new Deadline(taskData[1], taskData[3]));
                break;
            case "E":
                taskList.getTasks().add(new Event(taskData[1], taskData[3], taskData[4]));
                break;
            default:
                throw new DukeException("Error loading tasks from file!");
            }

            if (taskData[2].equals("T")) {
                taskList.getTasks().get(taskList.getTasks().size() - 1).mark();
            }
        }
        fileReader.close();
    }
}
