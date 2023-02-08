package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Storage solely handles any activities related to storage
 * including loading and saving data to a file.
 *
 * @author Guo-KeCheng
 */
public class Storage {

    private final String filePath;
    private final Ui ui;

    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Storage constructor.
     *
     * @param filePath String representation of a filepath
     * @param ui       Shared Ui Object
     */
    public Storage(String filePath, Ui ui) {
        assert filePath != null;
        assert ui != null;
        this.filePath = filePath;
        this.ui = ui;
    }

    /**
     * Saves Task in TaskList to a file.
     *
     * @param taskList Existing TaskList
     * @throws IOException If file does not exist and cannot be created
     */
    public void save(TaskList taskList) throws IOException {
        File file = new File(filePath);
        File parentFile = file.getParentFile();

        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file);

        for (Task task : taskList) {
            String str = task.encode();
            fw.write(str);
            fw.write(System.lineSeparator());
        }

        fw.close();
    }

    /**
     * Load data into a TaskList from a file.
     *
     * @param filePath String representation of filepath to load from
     * @return TaskList with previously saved data
     * @throws FileNotFoundException If source is not found
     */
    public TaskList load(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        TaskList taskList = new TaskList();

        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {

            String[] inputs = sc.nextLine().trim().split(" \\| ");

            TaskType taskType = TaskType.valueOf(inputs[0].toUpperCase());

            String task = inputs[2];
            boolean status = Boolean.parseBoolean(inputs[1]);

            switch (taskType) {
            case TODO:
                Task todo = new ToDo(task, status);
                taskList.add(todo);
                break;
            case DEADLINE:
                Task deadline = new Deadline(task, status, inputs[3]);
                taskList.add(deadline);
                break;
            case EVENT:
                Task event = new Event(task, status, inputs[3], inputs[4]);
                taskList.add(event);
                break;
            default:
                assert false : "Should not reach here"; // Will not reach here
            }
        }

        sc.close();
        return taskList;
    }
}
