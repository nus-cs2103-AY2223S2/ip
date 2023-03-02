package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.TaskList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

/**
 * The Storage class that is used to store and read the list of Task(s)
 */
public class Storage {
    static final String HOME_DIRECTORY = System.getProperty("user.dir");
    static final Path DEFAULT_DIRECTORY = Paths.get(HOME_DIRECTORY, "SavedList.txt");
    private final Path filePath;

    /**
     * Returns a Storage object with the filePath as the default Path
     */
    public Storage() {
        this.filePath = DEFAULTDIRECTORY;
    }

    /**
     * Returns a Storage object with filePath stored as the Path
     *
     * @param filePath Path of the stored list
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns an ArrayList of Task(s) loaded from the filePath
     *
     * @return ArrayList<Task> list of Task(s)
     * @throws DukeException if unable to read the file or the format of a line in the file is wrong
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            return this.readSavedFile(this.filePath);
        } catch (IOException e) {
            throw new DukeException("File is corrupted.");
        }
    }

    /**
     * Returns an ArrayList of Task(s) loaded from the Path given
     *
     * @param path Path directory of stored list of task(s)
     * @return ArrayList<Task> list of Task(s)
     * @throws IOException if unable to read the file
     * @throws DukeException if the format of a line in the file is wrong
     */
    public ArrayList<Task> readSavedFile(Path path) throws IOException, DukeException {
        try {
            Files.createFile(path);
            return new ArrayList<>();
        } catch (FileAlreadyExistsException e) {
            ArrayList<Task> arr = new ArrayList<>();
            List<String> savedList = Files.readAllLines(path);
            for (String task : savedList) {
                try {
                    arr.add(readLineToTask(task));
                } catch (WrongTaskFormatException | ArrayIndexOutOfBoundsException wrongFormat) {
                    throw new DukeException("File format of tasks is wrong.");
                }
            }
            return arr;
        }
    }

    /**
     * Reads the line taskDescription and formats it into the correct type of Task
     *
     * @param taskDescription String description of the task
     * @return Task correct type of Task with information
     * @throws WrongTaskFormatException if the format of the taskDescription is wrong
     */
    public Task readLineToTask(String taskDescription) throws WrongTaskFormatException {
        String[] items = taskDescription.split(" \\| ");
        boolean isDone = getIsDone(items[1]);
        Task task = new Task(taskDescription, isDone);
        switch(items[0]) {
            case "T":
                task = new ToDo(items[2], isDone);
                break;
            case "D":
                task = new Deadline(items[2], items[3], isDone);
                break;
            case "E":
                String[] timeOfEvent = items[3].split("-");
                task = new Event(items[2], timeOfEvent[0], timeOfEvent[1], isDone);
                break;
            default:
                throw new WrongTaskFormatException("Invalid task.Task String Format");
        }
        return task;
    }

    /**
     * Returns true if the doneString is 1 and false otherwise (0)
     *
     * @param doneString String
     * @return boolean
     */
    public boolean getIsDone(String doneString) {
        return doneString.equals("1");
    }

    /**
     * Saves the TaskList into the filePath
     *
     * @param tasks TaskList list of Tasks
     */
    public void saveList(TaskList tasks) {
        try {
            // no need to deleteIfExists as BufferedWriter automatically clears all prev input
            BufferedWriter fileWriter = Files.newBufferedWriter(this.filePath);
            for (Task task : tasks.getTasks()) {
                fileWriter.write(task.saveString());
                fileWriter.newLine();
            }
            fileWriter.close();
        } catch (IOException e) {
            // unsure of what cases would throw IOException under write()
        }
    }
}
