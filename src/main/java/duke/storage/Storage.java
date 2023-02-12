package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.dukeexception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;


/**
 * Class that handles saving and reading tasks saved on the local hard disk
 */
public class Storage {
    private File allTasks;
    private File taskFolder;

    /**
     * Constructor
     * @param filePath A string representing the location of the local file containing all tasks
     * @param folderPath A string representing the location of the folder containing the above file
     */
    public Storage(String filePath, String folderPath) {
        this.allTasks = new File(filePath);
        this.taskFolder = new File(folderPath);
    }

    /**
     * Loads the local tasks into the TaskList if they exist. prints customised
     * messages if the file and/or folder does not exist, and create them accordingly
     * @return an empty ArrayList of Tasks if no local tasks are found, or an ArrayList
     *         containing all local tasks if they are found.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> defaultTasks = new ArrayList<>();
        if (!taskFolder.exists()) {
            taskFolder.mkdir();
            File f = new File(taskFolder, "task.txt");
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new DukeException("Error creating file: " + e.getMessage());
            }
        } else if (!allTasks.exists()) {
            File f = new File(taskFolder, "task.txt");
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new DukeException("Error creating file: " + e.getMessage());
            }
        } else {
            try {
                defaultTasks = loadDefaultTasks(new ArrayList<Task>(), allTasks);
            } catch (FileNotFoundException e) {
                throw new DukeException("Could not load the default tasks: " + e.getMessage());
            }
        }
        return defaultTasks;
    }
    /**
     * method that loads all local tasks to an ArrayList of Tasks to be passed to TaskList
     * @param tasks default ArrayList of Tasks
     * @param file local file that contains all local tasks
     * @return an ArrayList of Tasks containing all local tasks
     * @throws FileNotFoundException if the local file is not found.
     */
    private static ArrayList<Task> loadDefaultTasks(ArrayList<Task> tasks, File file)
            throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String[] lineArr = s.nextLine().split("/");
            switch (lineArr[0]) {
            case "D":
                Deadline deadline = new Deadline(lineArr[1], Integer.parseInt(lineArr[2]), lineArr[3]);
                for (int i = 4; i < lineArr.length; i++) {
                    deadline.addTag(lineArr[i]);
                }
                tasks.add(deadline);
                break;
            case "T":
                Todo todo = new Todo(lineArr[1], Integer.parseInt(lineArr[2]));
                for (int i = 3; i < lineArr.length; i++) {
                    todo.addTag(lineArr[i]);
                }
                tasks.add(todo);
                break;
            case "E":
                Event event = new Event(lineArr[1], Integer.parseInt(lineArr[2]), lineArr[3], lineArr[4]);
                for (int i = 5; i < lineArr.length; i++) {
                    event.addTag(lineArr[i]);
                }
                tasks.add(event);
                break;
            default:
                continue;
            }
        }
        s.close();
        return tasks;
    }

    /**
     * Updates the locally saved tasks according to the TaskList
     * @param tasks A TaskList represeting all temporary tasks
     * @throws IOException if there is error when updating the local task
     */
    public void updateLocal(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.allTasks);
        fw.write(tasks.getWriteString());
        fw.close();
    }
}
