package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tasks.Deadline;
import tasks.Event;
import tasks.Priority;
import tasks.Task;
import tasks.TaskComparator;
import tasks.TaskType;
import tasks.Todo;

/**
 * Represents the Storage that helps to store the tasks entered by the user
 */
public class Storage {
    private final String filePath;
    private File file;

    /**
     * Constructs a new Storage with the specified file path.
     *
     * @param filePath the file path to the storage file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
        file.getParentFile().mkdirs();
    }

    /**
     * Returns the TaskType based on the String input given
     * by iterating through the existing TaskType values
     *
     * @return the TaskType based on the String input given
     */
    public TaskType getTaskType(String input) {
        for (TaskType t : TaskType.values()) {
            if (t.isEqual(input)) {
                return t;
            }
        }
        return TaskType.DEFAULT;
    }

    /**
     * Loads any existing data from the filePath of the Storage (if such a file in the filePath exists),
     * parses each line into a corresponding task and then adds each task into a
     * newly initialised arraylist of tasks. Returns the arraylist of tasks loaded.
     *
     * @return the arraylist of tasks loaded from the storage file (if any)
     * @throws Exception
     */
    public List<Task> loadFile() throws IOException {
        Validator validator = new Validator();
        List<Task> initTasks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();
        while (line != null) {
            line = line.trim();
            String[] strArr = line.split(" \\| ");
            TaskType type = getTaskType(strArr[0]);
            boolean isCompleted = Integer.parseInt(strArr[1]) == 1;
            String taskDesc = strArr[2];
            Priority priority = Priority.getPriority(strArr[3]);
            // TODO: Handle error case when strArr.length < 2 after splitting by ' | '
            switch (type) {
            case TODO:
                initTasks.add(new Todo(taskDesc, isCompleted, priority));
                break;
            case DEADLINE:
                if (validator.isDateValid(strArr[4])) {
                    LocalDate byDate = LocalDate.parse(strArr[4]);
                    initTasks.add(new Deadline(taskDesc, isCompleted, byDate, priority));
                }
                break;
            case EVENT:
                initTasks.add(new Event(taskDesc, isCompleted, strArr[4], strArr[5], priority));
                break;
            default:
                initTasks.add(new Task(taskDesc, isCompleted, type, priority));
            }
            line = reader.readLine();
        }
        Collections.sort(initTasks, new TaskComparator());
        return initTasks;
    }

    /**
     *  Saves tasks into the Storage file by iterating through the specified TaskList.
     *  Also handles any saving errors by using the Ui to display the error message
     *
     * @param taskList the tasklist to save
     * @param ui the Ui to help inform the user of the error
     */
    public void saveListToFile(TaskList taskList, Ui ui) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(filePath, false);
            List<Task> list = taskList.getTaskList();
            for (Task t : list) {
                writer.write(t.parseToSave() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            // TODO: Better error handling needed, Throw error forward instead
            ui.getSavingError();
        }
    }

}
