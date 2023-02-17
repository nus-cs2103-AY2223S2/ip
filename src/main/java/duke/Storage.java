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
            String str;
            for (Task task : listOfTasks) {
                str = task.toString();
                str = str.replace("[ ]", " | 0 |");
                str = str.replace("[X]", " | 1 |");
                str = str.replace("[", "");
                str = str.replace("]", "");
                if (str.startsWith("D")) {
                    str = str.replace("(by:", "|");
                    str = str.replace(")", "");
                } else if (str.startsWith("E")) {
                    str = str.replace("(from:", "|");
                    str = str.replace(" to: ", " - ");
                    str = str.replace(")", "");
                }
                str = str + "\n";
                fw.write(str);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException();
        }
    }

    /**
     * Returns the tasks from the task file.
     *
     * @return Array list of tasks.
     * @throws DukeException If the file was not found or has incorrect date time format or has unrecognized tasks.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> listOfTasks = new ArrayList<>(100);
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            Task task;
            String description, str, taskInfo;
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");

            while (s.hasNext()) {
                str = s.nextLine();
                taskInfo = str.substring(8);
                switch (str.charAt(0)) {
                case 'T':
                    description = taskInfo;
                    task = new Todo(description);
                    break;
                case 'D':
                    int byIdx = taskInfo.indexOf("|");
                    description = taskInfo.substring(0, byIdx - 1);
                    LocalDateTime by = LocalDateTime.parse(taskInfo.substring(byIdx + 2), format);
                    task = new Deadline(description, by);
                    break;
                case 'E':
                    int fromIdx = taskInfo.indexOf("|");
                    int toIdx = taskInfo.indexOf("-");
                    description = taskInfo.substring(0, fromIdx - 1);
                    LocalDateTime from = LocalDateTime.parse(taskInfo.substring(fromIdx + 2, toIdx - 1), format);
                    LocalDateTime to = LocalDateTime.parse(taskInfo.substring(toIdx + 2), format);
                    task = new Event(description, from, to);
                    break;
                default:
                    throw new DukeException();
                }
                if (str.charAt(4) == '1') {
                    task.markAsDone();
                }
                listOfTasks.add(task);
                assert listOfTasks.get(listOfTasks.size() - 1) == task : "Task should be added to the task list";
            }
        } catch (FileNotFoundException | DateTimeParseException e) {
            throw new DukeException();
        }
        return listOfTasks;
    }
}















