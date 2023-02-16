package duke.storage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Storage class that manage loading of tasks from file and saving of tasks in file
 */
public class Storage {
    private File taskFile;

    /**
     * Constructor of Storage to create file
     */
    public Storage(String filepath) {
        try {
            taskFile = new File(filepath);
            if (!taskFile.exists()) {
                checkDirectoryExist("./data/");
                checkFileExist();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Check if the directory of the file path exists or not, make directory if it does not exist
     * @param directoryPath
     */
    public void checkDirectoryExist(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Check if file exist, create a new text file to be stored in directory path if file does not exist
     * @throws IOException
     */
    public void checkFileExist() throws IOException {
        if (!taskFile.exists()) {
            taskFile.createNewFile();
        }
    }

    /**
     * Load tasks from file
     * @return List of tasks
     * @throws DukeException If unable to read file
     */
    public List<Task> load() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        try {
            BufferedReader myReader = new BufferedReader(new FileReader(this.taskFile));
            String line = myReader.readLine();
            while (line != null) {
                // task is saved in the following format:
                // taskType /// completionStatus /// taskDescription /// startDate for event or deadline /// endDate
                String[] str = line.split(" /// ", 5);
                Task task = null;

                switch (str[0]) {
                case "T":
                    task = new ToDo(str[2]);
                    break;
                case "D":
                    task = new Deadline(str[2], str[3]);
                    break;
                case "E":
                    task = new Event(str[2], str[3], str[4]);
                    break;
                default:
                    break;
                }
                if (str[1].equals("[X]")) {
                    task.markTask();
                }
                taskList.add(task);
                line = myReader.readLine();
            }
            myReader.close();
        } catch (IOException e) {
            throw new DukeException("Unable to load file");
        }
        return taskList;
    }

    /**
     * Save the list of tasks in the file
     * @param taskList List of tasks to be saved
     * @throws IOException When tasks cannot be saved
     */
    public void save(TaskList taskList) {
        try {
            BufferedWriter myWriter = new BufferedWriter(new FileWriter(this.taskFile));
            for (int i = 0; i < taskList.getSize(); i++) {
                Task t = taskList.getTaskList().get(i);
                myWriter.write(t.toBeSaved() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("There is an error in saving tasks!");
        }
    }


}
