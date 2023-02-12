package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.InvalidDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * A file manager to manage the saving and retrieval of users' data.
 *
 * @author Andre Lin HuiKai
 * @version CS2103T AY22/23 Semester 2
 */
public class FileManagement {
    private File file;
    private String filePath;
    private String dataDirectory;

    /**
     * Constructor for FileManagement.
     */
    public FileManagement() {
        this.dataDirectory = "./data/";
        this.filePath = this.dataDirectory + "duke.txt";
        this.file = new File(this.filePath);
        File directory = new File(this.dataDirectory);
        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Saves the list of tasks made by the user.
     * @param taskList TaskList of tasks provided by the user.
     */
    public void save(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            for (Task task : taskList.getTasks()) {
                writer.write(task.encode() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Retrieves saved information about the user's tasks from the previous session.
     * @return List of previously saved tasks.
     */
    public List<Task> retrieve() {
        Scanner scanner = null;
        List<Task> taskList = new ArrayList<>();
        try {
            scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String encoded = scanner.nextLine();
                taskList.add(this.decodeTask(encoded));
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (InvalidDateException e) {
            System.out.println(e);
        } finally {
            scanner.close();
        }
        return taskList;
    }


    /**
     * Decode encrypted information regarding a task.
     * @param task String encoding of task.
     * @return The task object constructed from its encoded string representation.
     * @throws InvalidDateException Throws an InvalidDateException if an invalid date format was saved for any task.
     */
    private Task decodeTask(String task) throws InvalidDateException {
        String[] components = task.split(" ### ");
        String command = components[0];
        String toMark = components[1];
        String des = components[2];
        Task decoded = null;
        switch (command) {
        case "todo":
            decoded = new ToDo(des);
            break;
        case "deadline":
            decoded = new Deadline(des, components[3]);
            break;
        case "event":
            decoded = new Event(des, components[3], components[4]);
            break;
        default:
            break;
        }
        if (toMark.equals("true")) {
            decoded.markTask();
        }
        return decoded;
    }
}
