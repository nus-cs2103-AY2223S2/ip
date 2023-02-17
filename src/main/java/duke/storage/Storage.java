package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.TaskList;
import duke.Todo;

/**
 * Storage class to handle all files related activities such as creation of duke.txt,
 * loading and saving data from duke.txt.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor method for Storage.
     * @param filePath path to duke.txt
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        checkFile(filePath);
    }

    /**
     * check if duke.txt exists, creates it otherwise
     * @param filePath relative/absolute path to duke.txt
     */
    private void checkFile(String filePath) {
        File file = new File(filePath);
        if (!file.isFile()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Handles the saving of data from the list of tasks to duke.txt
     * @param taskList the list of tasks
     */
    public void saveData(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.getTask(i + 1);
                fileWriter.write(t.formatForFile());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Handles the loading of data from duke.txt to the list of tasks
     * @return a list of tasks
     * @throws FileNotFoundException if the file does not exist in the filePath given
     */
    public List<Task> loadFile() throws FileNotFoundException {
        List<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (file.isFile()) {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] data = sc.nextLine().split(" \\| ");
                Task task = null;
                switch (data[0]) {
                case "T":
                    task = new Todo(data[2]);
                    break;
                case "D":
                    task = new Deadline(data[2], data[3]);
                    break;
                case "E":
                    task = new Event(data[2], data[3], data[4]);
                    break;
                default:
                    task = null;
                }
                if (data[1].equals("1")) {
                    task.marked();
                }
                taskList.add(task);
            }
            sc.close();
        }
        return taskList;
    }
}
