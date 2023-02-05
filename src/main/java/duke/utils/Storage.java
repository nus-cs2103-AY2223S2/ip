package duke.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.Duke;
import duke.exceptions.DukeException;
import duke.taskers.Deadline;
import duke.taskers.Event;
import duke.taskers.Task;
import duke.taskers.Todo;

/**
 * Storage class the stores data into a file.
 */
public class Storage {

    private String filePath;
    private String dirPath;
    private File dukeFile;

    /**
     * Constructor for Storage.
     *
     * @param filePath The path to the duke file.
     */
    public Storage(String filePath) {
        assert !filePath.equals("");
        this.filePath = filePath;
        int lastIndexOfSlash = this.filePath.lastIndexOf(File.separator);
        this.dirPath = this.filePath.substring(0, lastIndexOfSlash);
        this.dukeFile = new File(this.filePath);
    }

    /**
     * The function that formats the task to store into the duke file.
     *
     * @param t The task to be appended to the file.
     * @return The string representation to be appended to the file.
     */
    public static String taskStringFormatter(Task t) {
        return t.formatStringForFile();
    }


    /**
     * Writes the tasks to the duke file.
     *
     * @param item The task that is inserted into the duke file.
     */
    public void writeToFile(Task item) {
        try {
            String fileInputString = taskStringFormatter(item);
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.write(fileInputString + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads all the items from the duke file.
     */
    public ArrayList<Task> loadFromFile() throws DukeException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        try {
            Files.createDirectories(Paths.get(this.dirPath));
            Scanner sc = new Scanner(this.dukeFile);

            while (sc.hasNext()) {
                String str = sc.nextLine();
                String[] valueArr = str.split("/");

                Task thisTask = null;
                String doneOrNotStr = valueArr[1].trim();
                assert doneOrNotStr.equals("1") || doneOrNotStr.equals("0");
                boolean doneOrNot = doneOrNotStr.equals("1");
                String type = valueArr[0].toLowerCase().trim();

                switch (type) {
                case Todo.TASK_TYPE:
                    thisTask = new Todo(valueArr[2], doneOrNot);
                    break;
                case Deadline.TASK_TYPE:
                    LocalDateTime end = Duke.createLocalDateTime(valueArr[3]);
                    thisTask = new Deadline(valueArr[2], doneOrNot, end);
                    break;
                case Event.TASK_TYPE:
                    LocalDateTime startDate = Duke.createLocalDateTime(valueArr[3]);
                    LocalDateTime endDate = Duke.createLocalDateTime(valueArr[4]);
                    thisTask = new Event(valueArr[2], doneOrNot, startDate, endDate);
                    break;
                default:
                    throw new DukeException("Something went wrong");
                }
                loadedTasks.add(thisTask);
            }
        } catch (IOException e) {
            // do nothing
        }
        return loadedTasks;
    }


    /**
     * The function that deletes the file and redoes it.
     *
     * @param listOfThings Array of Tasks to be inside the new file.
     */
    public void deleteFileAndRedo(ArrayList<Task> listOfThings) {
        if (this.dukeFile.delete()) {
            for (Task item : listOfThings) {
                writeToFile(item);
            }
        } else {
            System.out.println("File deletion failed");
        }
    }


}
