package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Handles the storage of the task list.
 * It reads from the file and writes to the file.
 * It also creates the file if it does not exist.
 * The file is stored in the data folder.
 */
public class Storage {

    private String filePath = "data/duke.txt";
    private File file;

    /**
     * Creates the storage object.
     * @throws DukeException If the file cannot be created.
     */
    public Storage() throws DukeException {
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                assert file.exists() : "File creation failed.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("File creation failed.");
        }
    }

    /**
     * Reads the file and returns the task list
     * @return The task list.
     * @throws DukeException If the file cannot be read.
     */
    public TaskList readFromFile() throws DukeException {
        TaskList taskList = new TaskList();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] task = sc.nextLine().split(" \\| ");
                if ("T".equals(task[0])) {
                    taskList.addTask(new ToDo(task[2]));
                } else if ("D".equals(task[0])) {
                    taskList.addTask(new Deadline(task[2], task[3]));
                } else if ("E".equals(task[0])) {
                    taskList.addTask(new Event(task[2], task[3], task[4]));
                }
                if (task[1].equals("1")) {
                    taskList.markTask(taskList.getTaskList().size() - 1);
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("File reading failed.");
        }
        return taskList;
    }

    /**
     * Writes the task list to the file.
     * @param taskList The task list to be written.
     * @throws DukeException If the file cannot be written.
     */
    public void writeToFile(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                fw.write(task.toFileString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("File writing failed.");
        }
    }

    /**
     * Clears the file.
     * @throws DukeException If the file cannot be cleared.
     */
    public void clearFile() throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("File clearing failed.");
        }
    }

}
