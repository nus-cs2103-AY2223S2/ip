package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.TaskList;

import java.io.BufferedWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Storage {
    static final String HOMEDIRECTORY = System.getProperty("user.dir");
    static final Path DEFAULTDIRECTORY = Paths.get(HOMEDIRECTORY, "SavedList.txt");
    private final Path filePath;
    public Storage() {
        this.filePath = DEFAULTDIRECTORY;
    }
    public Storage(Path filePath) {
        this.filePath = filePath;
    }
    public ArrayList<Task> load() throws DukeException {
        try {
            return this.readSavedFile(this.filePath);
        } catch (IOException e) {
            throw new DukeException("File is corrupted.");
        }
    }
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
    public boolean getIsDone(String doneString) {
        return doneString.equals("1");
    }
    public void saveList(TaskList tasks) {
        try {
            // no need to deleteIfExists as BufferedWritter automatically clears all prev input
            BufferedWriter fileWriter = Files.newBufferedWriter(this.filePath);
            for (Task task : tasks.getTasks()) {
                fileWriter.write(task.saveString());
                fileWriter.newLine();
            }
            fileWriter.close();
        } catch (IOException e) {
            // unsure of what cases would throw IOException under deleteIfExists
        }
    }
}
