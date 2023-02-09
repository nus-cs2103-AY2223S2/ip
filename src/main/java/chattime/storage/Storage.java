package chattime.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import chattime.exception.ChattimeException;
import chattime.task.Deadline;
import chattime.task.Event;
import chattime.task.Task;
import chattime.task.Todo;
import chattime.ui.Ui;

/**
 * Represents Storage object to execute the services of loading, storing and updating data.
 */
public class Storage {

    private static final String DEFAULT_FILE_PATH = "data/chattimeTask.txt";

    private ArrayList<Task> initialTasks;
    private File file;
    private String filePath;
    private Ui ui;

    /**
     * Creates a Storage object with UI object to handle UI requirements and path of storage.
     *
     * @param passedUi UI object from bot to handle UI command.
     * @param path Storage path specified.
     * @throws IOException When memory initialization error.
     */
    public Storage(Ui passedUi, String path) throws IOException {
        ui = passedUi;
        filePath = (path.equals("") ? DEFAULT_FILE_PATH : path);
        openFile(filePath);
        initialTasks = new ArrayList<>();
    }

    /**
     * Opens file at storage path, creates one at the given path if the file is not found.
     *
     * @param filePath Specified storage path.
     * @throws IOException When file cannot be opened.
     */
    public void openFile(String filePath) throws IOException {
        file = new File(filePath);

        if (!file.exists()) {
            checkAndCreateDirectory(file);
            checkAndCreateFile(file);
        }
    }

    /**
     * Checks whether directory in path exists, creates one if not.
     *
     * @param file File instance of the specified file path.
     * @throws IOException When directory cannot be created.
     */
    private void checkAndCreateDirectory(File file) throws IOException {
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            throw new IOException("New directory cannot be created!");
        }
    }

    /**
     * Checks whether file in path exists, creates one if not.
     *
     * @param file File instance of the specified file path.
     * @throws IOException When file cannot be created.
     */
    private void checkAndCreateFile(File file) throws IOException {
        if (!file.createNewFile()) {
            throw new IOException("New file cannot be created!");
        }
    }

    /**
     * Prepares loading data into current task list.
     *
     * @return An arraylist of stored data to be imported into current TaskList object.
     * @throws ChattimeException Returns error message.
     */
    public ArrayList<Task> loadData() throws IOException, ChattimeException {
        BufferedReader loader = new BufferedReader(new FileReader(file));
        String task = loader.readLine();

        while (task != null) {
            importData(task);
            task = loader.readLine();
        }
        return initialTasks;
    }

    private void importData(String task) throws ChattimeException {
        String[] taskSplit = task.split(" @ ", 7);
        Task inputTask = createTaskFromStorage(taskSplit[0], taskSplit);

        if (taskSplit[1].equals("1")) {
            inputTask.markAsDone();
        }

        initialTasks.add(inputTask);
    }

    /**
     * Create task of different types from data storage.
     *
     * @param taskCode Code of task types.
     * @param taskSplit Processed storage data by splitting separation.
     * @return An arraylist of stored data to be imported into current TaskList object.
     * @throws ChattimeException Returns error message.
     */
    private Task createTaskFromStorage(String taskCode, String[] taskSplit) throws ChattimeException {
        switch (taskCode) {
        case "T":
            return new Todo(taskSplit[2]);

        case "D":
            return createDeadlineObject(taskSplit);

        case "E":
            return createEventObject(taskSplit);

        default:
            throw new ChattimeException("Task type error : " + taskSplit[0]);
        }
    }

    /**
     * Creates deadline object from storage string.
     *
     * @param taskSplit Processed storage string.
     * @return New deadline object respective to the storage string.
     * @throws ChattimeException Handle datetime format mismatch error.
     */
    private Deadline createDeadlineObject(String[] taskSplit) throws ChattimeException {
        try {
            LocalDate byDate = LocalDate.parse(taskSplit[3]);
            LocalTime byTime = (taskSplit[4].equals("0") ? null : LocalTime.parse(taskSplit[4]));

            return new Deadline(taskSplit[2], byDate, byTime);
        } catch (DateTimeParseException e) {
            throw new ChattimeException("Datetime error in storage!");
        }
    }

    /**
     * Created event object from storage string.
     *
     * @param taskSplit Processed storage string.
     * @return New event object respective to the storage string.
     * @throws ChattimeException Handle datetime format mismatch error.
     */
    private Event createEventObject(String[] taskSplit) throws ChattimeException {
        try {
            LocalDate fromDate = LocalDate.parse(taskSplit[3]);
            LocalTime fromTime = LocalTime.parse(taskSplit[4]);
            LocalDate toDate = LocalDate.parse(taskSplit[5]);
            LocalTime toTime = LocalTime.parse(taskSplit[6]);

            return new Event(taskSplit[2], fromDate, fromTime, toDate, toTime);

        } catch (DateTimeParseException e) {
            throw new ChattimeException("Datetime error in storage!");
        }
    }

    /**
     * Saves recently added task into storage file.
     *
     * @param task Task recently added.
     */
    public void saveToFile(Task task) throws IOException {
        String writeString = task.toDataString();

        writeToFile(writeString + System.lineSeparator(), true);
    }

    /**
     * Updates storage file by delete or update task of specified index.
     *
     * @param index Index of the removed or updated task.
     * @param task Updated task, takes no parameter in delete condition.
     * @throws IOException When error taking input or saving file.
     */
    public void rewriteFile(int index, Task... task) throws IOException {
        StringBuilder updateString = checkWriteContent(index, task);
        writeToFile(updateString.toString(), false);
    }

    private StringBuilder checkWriteContent(int index, Task... task) throws IOException {
        BufferedReader lineSearch = new BufferedReader(new FileReader(file));
        String content = lineSearch.readLine();
        int lineCount = 1;
        StringBuilder updateString = new StringBuilder();

        while (content != null) {
            if (lineCount == index) {
                try {
                    content = task[0].toDataString();
                } catch (IndexOutOfBoundsException e) {
                    lineCount++;
                    content = lineSearch.readLine();
                    continue;
                }
            }
            updateString.append(content).append(System.lineSeparator());
            lineCount++;
            content = lineSearch.readLine();
        }
        checkEmptyTask(lineCount, index);
        return updateString;
    }

    private void checkEmptyTask(int lineCount, int index) {
        if (lineCount < index) {
            throw new IndexOutOfBoundsException("Task not saved in storage!");
        }
    }



    /**
     * Common method to write in the storage file.
     *
     * @param content Data to be written in.
     * @param append Flag that determines whether the content should append or replace the entire storage file.
     * @throws IOException When error taking input or saving file.
     */
    private void writeToFile(String content, boolean append) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, append));
        assert !content.equals("") : "Storing empty data!";
        writer.write(content);
        writer.close();
    }
}
