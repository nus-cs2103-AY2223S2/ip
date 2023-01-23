import java.io.FileWriter;
import java.io.IOException;

/**
 *  Storage to handle loading and saving of TaskList
 *  (before interacting with user and after interacting with user)
 */
public class Storage {
    private final String filePath;

    /**
     *  Constructor to create data storage object
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves data from TaskList and store into file specified by this.filePath
     *
     * @params list to be saved
     */
    public void saveData(TaskList taskList) throws DukeException{
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            for (int i = 0; i < taskList.getSize(); i ++) {
                Task task = taskList.getTask(i);
                StringBuilder taskString = new StringBuilder();
                taskString.append(task.eventType());
                taskString.append(" | " + task.getStatusIcon() + " | ");
                taskString.append(task.getStorageDetails() + "\n");
                fileWriter.write(taskString.toString());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
