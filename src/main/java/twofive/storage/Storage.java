package twofive.storage;

import twofive.data.TaskList;
import twofive.task.Task;
import twofive.utils.FileParser;

import twofive.exception.EmptyDescriptionException;
import twofive.exception.EmptyStartTimeException;
import twofive.exception.InvalidTaskTypeException;
import twofive.exception.TaskDoneException;
import twofive.exception.EmptyEndTimeException;
import twofive.exception.EmptyDeadlineException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a storage responsible for loading and saving tasks saved
 * in a local file.
 */
public class Storage {
    private File taskFile;

    public Storage(String filePath) throws IOException {
        this.taskFile = new File(filePath);
        this.taskFile.getParentFile().mkdirs(); // Create parent directories if absent
        this.taskFile.createNewFile(); // Create task file if absent
    }

    /**
     * Returns an ArrayList containing Task objects which are loaded from
     * the local task file.
     *
     * @return ArrayList consisting of all tasks saved locally.
     * @throws FileNotFoundException If local file cannot be found.
     * @throws EmptyDescriptionException If the description is absent in one or more tasks.
     * @throws InvalidTaskTypeException If the task type is invalid for one or more tasks.
     * @throws EmptyDeadlineException If the deadline is absent in one or more Deadline tasks.
     * @throws EmptyEndTimeException If the end time is absent in one or more Event tasks.
     * @throws EmptyStartTimeException If the start time is absent in one or more Event tasks.
     * @throws TaskDoneException If a task that is already done is being marked as done.
     * @throws DateTimeParseException If the date and time format retrieved from the file is invalid.
     */
    public ArrayList<Task> load() throws FileNotFoundException, EmptyDescriptionException, InvalidTaskTypeException,
            EmptyDeadlineException, EmptyEndTimeException, EmptyStartTimeException, TaskDoneException,
            DateTimeParseException {
        FileParser fileParser = new FileParser(this.taskFile);
        return fileParser.parseFile();
    }

    /**
     * Saves the current list of tasks to the local task file.
     *
     * @param tasks List of tasks currently added.
     * @throws IOException If an I/O error has occured
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(taskFile);
        fw.write(tasks.getSaveTasksString());
        fw.close();
    }
}
