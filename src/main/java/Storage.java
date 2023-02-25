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
    public Storage(Path filePath) {
        this.filePath = filePath;
    }
    public ArrayList<Task> load() throws IOException {
        return this.readSavedFile(this.filePath);
    }
    public ArrayList<Task> readSavedFile(Path path) throws IOException {
        try {
            Files.createFile(path);
            return new ArrayList<Task>();
        } catch (FileAlreadyExistsException e) {
            ArrayList<Task> arr = new ArrayList<>();
            List<String> savedList = Files.readAllLines(path);
            for (String task : savedList) {
                try {
                    arr.add(readLineToTask(task));
                } catch (WrongTaskFormatException | ArrayIndexOutOfBoundsException wrongFormat) {
//                    print("File format of tasks is wrong.\n" +
//                            "List now contains information up to line before wrongly formatted line.");
//                    printList(arr);
                    return arr;
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
//                return task;
                break;
            case "D":
                task = new Deadline(items[2], items[3], isDone);
//                return task;
                break;
            case "E":
                String[] timeOfEvent = items[3].split("-");
                task = new Event(items[2], timeOfEvent[0], timeOfEvent[1], isDone);
//                return task;
                break;
            default:
                throw new WrongTaskFormatException("Invalid Task String Format");
        }
        return task;
    }
    /**
     * @param doneString a simple string containing 0/1
     * @returns the int value
     */
    public boolean getIsDone(String doneString) {
        return doneString.equals("1");
    }
    void saveList(ArrayList<Task> arr) {
        try {
            // no need to deleteIfExists as BufferedWritter automatically clears all prev input
            BufferedWriter fileWriter = Files.newBufferedWriter(this.filePath);
            for (Task task : arr) {
                fileWriter.write(task.saveString());
                fileWriter.newLine();
            }
            fileWriter.close();
        } catch (IOException e) {
            // unsure of what cases would throw IOException under deleteIfExists
        }
    }
}
