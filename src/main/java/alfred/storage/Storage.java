package alfred.storage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import alfred.exceptions.AlfredException;
import alfred.task.Deadline;
import alfred.task.Event;
import alfred.task.Task;
import alfred.task.TaskList;
import alfred.task.ToDo;

/**
 * Represents the storage that deals with loading tasks from a file and
 * saving tasks in a file.
 */

public class Storage {

    private File dataFile;

    /**
     * Constructs a Storage object that deals with loading tasks from a file and
     * saving tasks in a file.
     * @param filePath The file that works with the storage object regarding loading and writing.
     */
    public Storage(String filePath) {
        dataFile = new File(filePath);
    }

    /**
     * Loads the tasks from the file into an array list
     * @return The array list containing all the tasks from the data file.
     * @throws AlfredException The error that is thrown out when the file cannot be read.
     */
    public ArrayList<Task> load() throws AlfredException {
        ArrayList<Task> tasks = new ArrayList<>();
        dataFile.getParentFile().mkdir();
        try {
            if (dataFile.createNewFile()) {
                return tasks;
            }
            Scanner sc = new Scanner(dataFile);
            return getTasksFromFile(tasks, sc);
        } catch (IOException e) {
            throw new AlfredException("Error, invalid file path");
        }
    }

    private ArrayList<Task> getTasksFromFile(ArrayList<Task> tasks, Scanner sc) throws AlfredException {
        while (sc.hasNext()) {
            String[] taskInfoArr = sc.nextLine().split(" \\| ");
            Task task;
            // What happens if the data in the file is not as the format given?
            tasks.add(getTask(taskInfoArr));
        }
        return tasks;
    }

    private Task getTask(String[] taskInfoArr) throws AlfredException {
        class CheckMark {
            public void isMark(int value, Task task) {
                if (value == 1) {
                    task.markAsDone();
                }
            }
        }
        CheckMark checkMark = new CheckMark();

        String taskType = taskInfoArr[0];
        Task task;
        try {
            switch (taskType) {
            case "T":
                task = new ToDo(taskInfoArr[2]);
                checkMark.isMark(Integer.parseInt(taskInfoArr[1]), task);
                break;
            case "D":
                task = new Deadline(taskInfoArr[2], taskInfoArr[3]);
                checkMark.isMark(Integer.parseInt(taskInfoArr[1]), task);
                break;
            case "E":
                String[] duration = taskInfoArr[3].split("-");
                task = new Event(taskInfoArr[2], duration[0], duration[1]);
                checkMark.isMark(Integer.parseInt(taskInfoArr[1]), task);
                break;
            default:
                throw new AlfredException("I'm sorry but there is an invalid task in the data file");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AlfredException("There is probably a missing separator in your file");
        }
        return task;
    }
    /**
     * Writes all the tasks in the task list into the data file.
     * @param tasks All the tasks in the task list.
     * @throws AlfredException The error given when writing tasks into the file results
     *     in an error.
     */
    public void write(TaskList tasks) throws AlfredException {
        tasks.writeToFile(dataFile);
    }
}
