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
 * class that handles saving and reading tasks saved on the local hard disk
 */
public class Storage {
    private File allTasks;
    private File taskFolder;
    private String loadStatus;

    /**
     * Constructor
     * @param filePath A string representing the location of the local file containing all tasks
     * @param folderPath A string representing the location of the folder containing the above file
     */
    public Storage(String filePath, String folderPath) {
        this.allTasks = new File(filePath);
        this.taskFolder = new File(folderPath);
        this.loadStatus = "";
    }

    /**
     * a method that loads the local tasks into the TaskList if they exist. prints customised
     * messages if the file and/or folder does not exist, and create them accordingly
     * @return an empty ArrayList of Tasks if no local tasks are found, or an ArrayList
     *         containing all local tasks if they are found.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> defaultTasks = new ArrayList<>();
        if (!taskFolder.exists()) {
            System.out.println("---The default duke.task.Task Folder is not found, creating data folder...");
            taskFolder.mkdir();
            System.out.println("---Task Folder created successfully");
            File f = new File(taskFolder, "task.txt");
            try {
                f.createNewFile();
                System.out.printf("---Task File created successfully\n---ready to create tasks\n");
            } catch (IOException e) {
                throw new DukeException("Error creating file: " + e.getMessage());
            }
        } else if (!allTasks.exists()) {
            System.out.println("The default tasks do not exist, creating default task file...");
            File f = new File(taskFolder, "task.txt");
            try {
                f.createNewFile();
                System.out.printf("---Task File created successfully\n---ready to create tasks\n");
            } catch (IOException e) {
                throw new DukeException("Error creating file: " + e.getMessage());
            }
        } else {
            try {
                defaultTasks = loadDefaultTasks(new ArrayList<Task>(), allTasks);
            } catch (FileNotFoundException e) {
                throw new DukeException("Could not load the default tasks: " + e.getMessage());
            }
            loadStatus += "\n\n---Default duke.task.Task List successfully loaded\n\n";
        }
        return defaultTasks;
    }

    public String getLoadStatus() {
        return loadStatus;
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
                tasks.add(new Deadline(lineArr[1], Integer.parseInt(lineArr[2]), lineArr[3]));
                break;
            case "T":
                tasks.add(new Todo(lineArr[1], Integer.parseInt(lineArr[2])));
                break;
            case "E":
                tasks.add(new Event(lineArr[1], Integer.parseInt(lineArr[2]), lineArr[3], lineArr[4]));
                break;
            default:
                continue;
            }
        }
        s.close();
        return tasks;
    }

    /**
     * method used to update the locally saved tasks according to the TaskList
     * @param tasks
     * @throws IOException
     */
    public void update(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.allTasks);
        fw.write(tasks.getWriteString());
        fw.close();
    }
}
