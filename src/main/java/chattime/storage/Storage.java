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
     */
    public Storage(Ui passedUi, String path) {
        ui = passedUi;
        filePath = (path.equals("") ? DEFAULT_FILE_PATH : path);
        openFile(filePath);
        initialTasks = new ArrayList<>();
    }

    /**
     * Opens file at storage path, creates one at the given path if the file is not found.
     *
     * @param filePath Specified storage path.
     */
    public void openFile(String filePath) {
        try {
            file = new File(filePath);

            if (!file.exists()) {
                checkAndCreateDirectory(file);
                checkAndCreateFile(file);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Checks whether directory in path exists, creates one if not.
     *
     * @param file File instance of the specified file path.
     * @throws IOException When directory cannot be created.
     */
    private void checkAndCreateDirectory(File file) throws IOException {
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                throw new IOException("New directory cannot be created!");
            }
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
     */
    public ArrayList<Task> loadData() {
        BufferedReader loader;

        try {
            loader = new BufferedReader(new FileReader(file));
            String task = loader.readLine();

            while (task != null) {
                String[] taskSplit = task.split(" @ ", 7);
                Task inputTask = null;

                switch (taskSplit[0]) {
                case "T":
                    inputTask = new Todo(taskSplit[2]);
                    break;

                case "D":
                    inputTask = createDeadlineObject(taskSplit);
                    break;

                case "E":
                    inputTask = createEventObject(taskSplit);
                    break;

                default:
                    throw new ChattimeException("Task type error : " + taskSplit[0]);
                }

                if (taskSplit[1].equals("1")) {
                    inputTask.markAsDone();
                }

                initialTasks.add(inputTask);
                task = loader.readLine();
            }
            return initialTasks;

        } catch (IOException | ChattimeException e) {
            System.err.println(e.getMessage());
        }
        return initialTasks;
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
            throw new ChattimeException("OOPS!!! Datetime error in storage!");
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
            throw new ChattimeException("OOPS!!! Datetime error in storage!");
        }
    }

    /**
     * Saves recently added task into storage file.
     *
     * @param task Task recently added.
     */
    public void saveToFile(Task task) {
        String writeString = task.toDataString();

        writeToFile(writeString + System.lineSeparator(), true);
    }

    /**
     * Updates storage file by delete or update task of specified index.
     *
     * @param index Index of the removed or updated task.
     * @param task Updated task, takes no parameter in delete condition.
     */
    public void rewriteFile(int index, Task... task) {
        BufferedReader lineSearch;

        try {
            lineSearch = new BufferedReader(new FileReader(file));
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

            if (lineCount < index) {
                throw new IndexOutOfBoundsException("Task not saved in storage!");

            } else {
                writeToFile(updateString.toString(), false);
            }
        } catch (IOException | IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Common method to write in the storage file.
     *
     * @param content Data to be written in.
     * @param append Flag that determines whether the content should append or replace the entire storage file.
     */
    private void writeToFile(String content, boolean append) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, append));
            writer.write(content);
            writer.close();

        } catch (IOException e) {
            ui.printError(e.getMessage());
        }

    }
}
