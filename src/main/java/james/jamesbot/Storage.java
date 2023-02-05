package james.jamesbot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    /** The path to the file where the task list is stored. */
    protected String filePath;

    /**
     * Constructor for Storage object.
     *
     * @param filePath The path to the file where the task list is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the updated task list to the file located at the filePath.
     *
     * @param taskList The string representation of the stored task list.
     */
    public void save(String taskList) {
        try {
            FileWriter fileWriter = new FileWriter("data/james.txt");
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
        TaskList result = new TaskList();

        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            File taskFile = new File(filePath);
            if (!taskFile.exists()) {
                taskFile.createNewFile();
            }

            Scanner scanner = new Scanner(taskFile);
            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                String[] taskData = task.split(",");

                switch (taskData[0]) {
                case "T":
                    ToDo todo = new ToDo(taskData[2]);
                    todo.setIsDone(taskData[1].equals("1"));
                    result.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(taskData[2],taskData[3]);
                    deadline.setIsDone(taskData[1].equals("1"));
                    result.add(deadline);
                    break;
                case "E":
                    Event event = new Event(taskData[2], taskData[3], taskData[4]);
                    event.setIsDone(taskData[1].equals("1"));
                    result.add(event);
                    break;
                }
            }
        } catch (IOException e) {
            throw new JamesException("OOPS, unable to load file");
        }

        return result;
    }
}
