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

public class Storage {
    private File taskFile;

    public Storage(String filePath) throws IOException {
        this.taskFile = new File(filePath);
        this.taskFile.getParentFile().mkdirs(); // Create parent directories if absent
        this.taskFile.createNewFile(); // Create task file if absent
    }

    // Loads saved task file
    public ArrayList<Task> load() throws FileNotFoundException, EmptyDescriptionException, InvalidTaskTypeException,
            EmptyDeadlineException, EmptyEndTimeException, EmptyStartTimeException, TaskDoneException,
            DateTimeParseException {
        FileParser fileParser = new FileParser(this.taskFile);
        return fileParser.parseFile();
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(taskFile);
        fw.write(tasks.getSaveTasksString());
        fw.close();
    }
}
