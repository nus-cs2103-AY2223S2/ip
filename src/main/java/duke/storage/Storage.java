package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
     * Constructor of Storage
     */
    public Storage(String filepath) {
        try {
            File directory = new File("./data/");
            taskFile = new File(filepath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!taskFile.exists()) {
                taskFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
                // line is saved in the following format:
                // taskType///completionStatus///taskDescription///startDate for event or deadline/// endDate
                String[] str = line.split("///", 5);
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
                    assert false : "Unable to load task!";
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
            for (Task task: taskList.getTaskList()) {
                myWriter.write(task.toBeSaved() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("There is an error in saving tasks.");
        }
    }


}
