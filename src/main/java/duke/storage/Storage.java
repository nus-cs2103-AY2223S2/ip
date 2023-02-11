package duke.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import duke.DateTimeParser;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Handles read and write of the (storable) taskList to/from file.
 */
public class Storage {

    /** Relative path of save file. */
    private static final String DEFAULT_SAVE_PATH = "./data/duke/tasks.csv";

    private final TaskList tasklist;
    private String saveFilePath;

    /**
     * Constructs instance of Storage using a reference to a TaskList.
     * Read/write is relative to this taskList.
     *
     * @param taskList Reference to a TaskList.
     */
    public Storage(TaskList taskList) {
        this.tasklist = taskList;
        this.saveFilePath = DEFAULT_SAVE_PATH;
    }

    /**
     * Writes the data of TaskList to file.
     */
    public void saveDataToFile() {
        assert tasklist != null : "Storage has no reference to taskList instance";

        // Prepare data into string format for saving
        String fileDataStr = tasklist.prepareFileSave();
        writeToFile(saveFilePath, fileDataStr);
    }

    /**
     * Reads save file and put them into the referenced TaskList.
     * Does nothing if save file does not exist.
     */
    public void loadDataFromFile() {
        assert tasklist != null : "Storage has no reference to taskList instance";

        DateTimeParser parser = new DateTimeParser();
        Path f = Paths.get(saveFilePath);

        // No saved data file -> do nothing
        if (!Files.exists(f)) {
            return;
        }

        // Purge taskList
        tasklist.removeAllTask();

        try {
            String currentLine;
            BufferedReader br = Files.newBufferedReader(f);

            while ((currentLine = br.readLine()) != null) {
                String[] taskInfo = currentLine.split(",");
                String taskType = taskInfo[0];
                boolean taskIsDone = Boolean.parseBoolean(taskInfo[1]);
                String taskDescription = taskInfo[2];

                if (taskType.compareTo("T") == 0) {
                    tasklist.add(new Todo(taskIsDone, taskDescription));
                } else if (taskType.compareTo("D") == 0) {
                    LocalDateTime dueDate = parser.parseDateTime(taskInfo[3], 'T');
                    tasklist.add(new Deadline(taskIsDone, taskDescription, dueDate));
                } else if (taskType.compareTo("E") == 0) {
                    LocalDateTime startDateTime = parser.parseDateTime(taskInfo[3], 'T');
                    LocalDateTime endDateTime = parser.parseDateTime(taskInfo[4], 'T');
                    tasklist.add(new Event(taskIsDone, taskDescription, startDateTime, endDateTime));
                }
            } // while loop
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IndexOutOfBoundsException e) {
            new Ui().warn("Corrupt data. Cannot load from file.");
        }
    }

    /**
     * Writes specified file content to specified file path.
     *
     * @param filePath Where to write to file.
     * @param fileContent What to write to file.
     */
    private void writeToFile(String filePath, String fileContent) {
        assert !filePath.isBlank() : "Cannot write file: file path blank";

        try {
            Path f = Paths.get(filePath);
            Files.createDirectories(f.getParent()); // Automatically create any non-existent parent directories
            if (!Files.exists(f)) {
                Files.createFile(f); // Create non-existent file
            }
            Files.writeString(f, fileContent); // Write to file
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the file path which this storage will write to.
     *
     * @return File path to write to.
     */
    String getSaveFilePath() {
        return saveFilePath;
    }

    /**
     * Updates the path of the desired save file with specified path.
     *
     * @param path New path of save file location.
     */
    void setSaveFilePath(String path) {
        saveFilePath = path;
    }

}
