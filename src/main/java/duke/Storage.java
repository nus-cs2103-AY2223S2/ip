package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with loading tasks from the
 * file and saving tasks in the file.
 */
public class Storage {
    /** Path of the task file */
    private final String filePath;
    private static final int TASK_TYPE_INDEX = 0;
    private static final int TASK_STATUS_INDEX = 4;
    private static final int TASK_INFO_INDEX = 8;
    private static final int MAX_SIZE = 100;

    /**
     * Initializes the path of the task file.
     *
     * @param filePath Path of the task file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to the task file.
     *
     * @param listOfTasks Array list of tasks.
     * @throws DukeException If the tasks cannot be saved to the file.
     */
    public void save(ArrayList<Task> listOfTasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : listOfTasks) {
                String stringTask = rewriteStringTask(task);
                fw.write(stringTask);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException();
        }
    }

    public String rewriteStringTask(Task task) {
        String str = task.toString();
        str = str.replace("[ ]", " | 0 |");
        str = str.replace("[X]", " | 1 |");
        str = str.replace("[", "");
        str = str.replace("]", "");

        if (str.startsWith("D")) {
            str = str.replace("(by:", "|");
            str = str.replace(")", "");
        }

        if (str.startsWith("E")) {
            str = str.replace("(from:", "|");
            str = str.replace(" to: ", " - ");
            str = str.replace(")", "");
        }

        str = str + "\n";
        return str;
    }

    /**
     * Returns the tasks from the task file.
     *
     * @return Array list of tasks.
     * @throws DukeException If the file was not found or has incorrect date time format or has unrecognized tasks.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> listOfTasks = new ArrayList<>(MAX_SIZE);
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String stringTask = s.nextLine();
                Task task = readTask(stringTask);
                listOfTasks.add(task);
                assert listOfTasks.get(listOfTasks.size() - 1) == task : "Task should be added to the task list";
            }
        } catch (FileNotFoundException | DateTimeParseException e) {
            throw new DukeException();
        }
        return listOfTasks;
    }

    public Task readTask(String stringTask) throws DukeException {
        Task task;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        String taskInfo = stringTask.substring(TASK_INFO_INDEX);
        switch (stringTask.charAt(TASK_TYPE_INDEX)) {
        case 'T':
            task = loadTodo(taskInfo);
            break;
        case 'D':
            task = loadDeadline(taskInfo, format);
            break;
        case 'E':
            task = loadEvent(taskInfo, format);
            break;
        default:
            throw new DukeException();
        }
        if (stringTask.charAt(TASK_STATUS_INDEX) == '1') {
            task.markAsDone();
        }
        return task;
    }

    public Task loadTodo(String taskInfo) {
        return new Todo(taskInfo);
    }

    public Task loadDeadline(String taskInfo, DateTimeFormatter format) throws DateTimeParseException {
        int byIdx = taskInfo.indexOf("|");
        String description = taskInfo.substring(0, byIdx - 1);
        LocalDateTime by = LocalDateTime.parse(taskInfo.substring(byIdx + 2), format);
        return new Deadline(description, by);
    }

    public Task loadEvent(String taskInfo, DateTimeFormatter format) throws DateTimeParseException {
        int fromIdx = taskInfo.indexOf("|");
        int toIdx = taskInfo.indexOf("-");
        String description = taskInfo.substring(0, fromIdx - 1);
        LocalDateTime from = LocalDateTime.parse(taskInfo.substring(fromIdx + 2, toIdx - 1), format);
        LocalDateTime to = LocalDateTime.parse(taskInfo.substring(toIdx + 2), format);
        return new Event(description, from, to);
    }
}















