package james.jamesbot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import james.exception.JamesException;
import james.task.Deadline;
import james.task.Event;
import james.task.TaskList;
import james.task.ToDo;



/**
 * Represents the file used to store task list data.
 */
public class Storage {

    protected File file;
    private String filePath = "storage/data/james.txt";

    /**
     * Constructs a Storage object.
     */
    public Storage() throws JamesException {
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                assert Files.exists(Paths.get(this.filePath)) : "The file, " + this.filePath + ", does not exist.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new JamesException("File could not be created.");
        }
    }

    /**
     * Saves the updated task list to the file located at the filePath.
     *
     * @param taskList The string representation of the stored task list.
     */
    public void save(String taskList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(taskList);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the saved task list in the file located at the filePath when JamesBot starts.
     * Else, creates a new file to store task list in the filePath.
     *
     * @return The TaskList stored in the file.
     * @throws JamesException If there are any problems loading the file.
     */
    public TaskList load() throws JamesException {
        TaskList taskList = new TaskList();

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String[] task = scan.nextLine().split(" \\| ");
                switch (task[0]) {
                case "T":
                    ToDo todo = new ToDo(task[2]);
                    todo.setIsDone(task[1].equals("1"));
                    taskList.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(task[2], task[3]);
                    deadline.setIsDone(task[1].equals("1"));
                    taskList.add(deadline);
                    break;
                case "E":
                    Event event = new Event(task[2], task[3], task[4]);
                    event.setIsDone(task[1].equals("1"));
                    taskList.add(event);
                    break;
                default:
                    throw new JamesException("No task types");
                }
            }
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new JamesException("File could not be loaded.");
        }
        return taskList;
    }
}
