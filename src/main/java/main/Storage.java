package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Class that reads from file at the start and updates file when changes are made.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs Storage.
     *
     * @param filePath Path of where file should be located.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a file is file does not exist. Otherwise, read from file to restore list of tasks.
     *
     * @return List of tasks that is read from file.
     * @throws DukeException Throws exception when there is error reading from file.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File f = new File(filePath);
            f.createNewFile();
            Scanner s = new Scanner(f);
            ArrayList<Task> arrOfTask = new ArrayList<>();
            while (s.hasNext()) {
                String[] arr = s.nextLine().split("\\|");
                Task t;
                if (arr[0].equals("T")) {
                    t = new Todo(arr[1]);
                } else if (arr[0].equals("D")) {
                    t = new Deadline(arr[1], LocalDate.parse(arr[3]));
                } else {
                    t = new Event(arr[1], LocalDate.parse(arr[3]), LocalDate.parse(arr[4]));
                }
                if (arr[2].equals("1")) {
                    t.taskDone();
                }
                arrOfTask.add(t);
            }
            return arrOfTask;
        } catch (IOException e) {
            throw new DukeException("Cannot open file");
        }
    }

    /**
     * Update file whenever changes are made to list of tasks.
     *
     * @param taskList TaskList class that will write to file.
     * @throws DukeException Throws exception when there is error writing from file.
     */
    public void writeFile(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            taskList.writeToFile(fw);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Cannot write to file");
        }
    }
}
