package lele.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import lele.exception.EmptyStorageException;
import lele.exception.LoadingFailureException;
import lele.task.Deadline;
import lele.task.Event;
import lele.task.Task;
import lele.task.TaskList;
import lele.task.Todo;


/**
 * Handles user data by creating, loading, updating the task list
 * in the user's hard drive.
 */
public class Storage {
    private final String filePath;
    private final ArrayList<Task> list;

    /**
     * Constructor to instantiate the file path
     * and array list.
     *
     * @param filePath Receives the file path from Lele.java
     */
    public Storage(String filePath) {
        assert filePath.equals("./data/lele.txt") : "Filepath invalid";
        this.filePath = filePath;
        this.list = new ArrayList<>();
    }

    /**
     * Loads data from the user's storage to the task list.
     * If there isn't existing data, a data directory and
     * duke.txt will be created.
     *
     * @return A task list for instantiating a TaskList.
     * @throws EmptyStorageException Thrown when no existing data found.
     */
    public ArrayList<Task> load() throws EmptyStorageException, LoadingFailureException {
        try {
            File f = new File(this.filePath);
            File dir = f.getParentFile();
            if (!dir.exists() && !f.exists()) {
                dir.mkdir();
                f.createNewFile();
                throw new EmptyStorageException("No existing data, creating a new file now");
            } else if (!f.exists()) {
                f.createNewFile();
            } else if (!dir.exists() && f.exists()) {
                // TODO: move file to data directory if file exists but not directory
            } else {
                // Dir + file exists, update taskList arr
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String input = sc.nextLine();
                    String[] inputArr;
                    inputArr = input.split("\\|");
                    this.loadTasks(inputArr, inputArr[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred, can't open file or something else happened");
            e.printStackTrace();
        }
        return this.list;
    }

    /**
     * Creates the relevant task type for loading
     * into the task list.
     *
     * @param inputArr Input received from parser.
     * @param taskType The type of task received.
     */
    public void loadTasks(String[] inputArr, String taskType) throws LoadingFailureException {
        switch (taskType) {
        case "T":
            Todo todo = new Todo(inputArr[2]);
            this.list.add(todo);
            todo.markStatus(inputArr[1]);
            break;
        case "D":
            String[] dateTime = inputArr[3].split(" ");
            Deadline deadline = new Deadline(inputArr[2], dateTime[0], dateTime[1]);
            this.list.add(deadline);
            deadline.markStatus(inputArr[1]);
            break;
        case "E":
            Event ev = new Event(inputArr[2], inputArr[3], inputArr[4]);
            this.list.add(ev);
            ev.markStatus(inputArr[1]);
            break;
        default:
            throw new LoadingFailureException("File couldn't be loaded: Possibly due to wrong format");
        }
    }

    /**
     * Updates lele.txt with the appropriate format.
     * E.g T|1|This is a to-do task, represents a to-do task
     * with done status and description of "This is a to-do task".
     * @param tasklist The current task list instance.
     * @throws IOException An error when there's an issue with file writing.
     */
    public void updateStorage(TaskList tasklist) throws IOException {
        Task task;
        StringBuilder sb = new StringBuilder();
        FileWriter fw = new FileWriter(filePath);
        for (Task value : tasklist.getList()) {
            task = value;
            assert task != null : "The current task list in storage shouldn't be empty";
            String taskName = task.getName();
            sb.append(taskName);
            sb.append("|");
            if (task.getStatusIcon().equals("X")) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            sb.append("|");
            sb.append(task.getDescription());
            if (taskName.equals("D")) {
                Deadline d = (Deadline) task;
                sb.append("|");
                sb.append(d.getDateTime());
            } else if (taskName.equals("E")) {
                Event ev = (Event) task;
                sb.append("|");
                sb.append(ev.getFrom());
                sb.append("|");
                sb.append(ev.getTo());
            }
            sb.append("\n");
        }
        fw.write(sb.toString().trim());
        fw.close();
    }
}
