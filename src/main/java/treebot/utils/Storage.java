package treebot.utils;

import treebot.interfaces.IStorage;
import treebot.tasks.Deadline;
import treebot.tasks.Event;
import treebot.tasks.Task;
import treebot.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage class that handles the reading and writing to the data text file
 * for data persistence.
 */
public class Storage implements IStorage {
    private File f;
    private String filePath;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Class constructor with a given file path to the text file.
     * @param filePath
     */
    public Storage(String filePath)  {
        this.f = new File(filePath);
        this.filePath = filePath;
    }

    /**
     * Loads the tasks in the text file and converts them into an ArrayList of Task.
     * @return an ArrayList of Task that was stored in the text file.
     * @throws FileNotFoundException
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException {
            Scanner sc = new Scanner(f);
            ArrayList<Task> taskList = new ArrayList<>();
            while (sc.hasNext()) {
                taskList.add(formatStringToTask(sc.nextLine()));

            }
            return taskList;

    }

    /**
     * Persists the current task list state to memory.
     * @param taskListState
     * @throws IOException
     */
    public void saveTasks(ArrayList<Task> taskListState) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : taskListState) {
            fw.write(task.toStorageFormatString() + System.lineSeparator());
        }
        fw.close();
    }

    private Task formatStringToTask(String formatString) {
        String[] splitStr = formatString.split("\\|");

        Task task;
        switch (splitStr[0]) {
        case "T":
            task = new Todo(splitStr[2]);
            break;
        case "D":
            task =  new Deadline(splitStr[2], LocalDateTime.parse(splitStr[3], formatter));
            break;
        case "E":
            task = new Event(splitStr[2], LocalDateTime.parse(splitStr[3], formatter), LocalDateTime.parse(splitStr[4], formatter));
            break;
        default:
            task =  null;

        }

        if (splitStr[1].equals("1")) {
            task.markAsDone();
            return task;
        }

        return task;
    }


}
