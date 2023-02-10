package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Contains the filepath where previous data is stored.
 * Allows for retrieval of the previously saved TaskList.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath Filepath where previous data is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the filepath.
     * @return Filepath.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Returns previously saved TaskList if necessary files exist. Creates the necessary files otherwise.
     * @return A List of Tasks to recreate the previously saved TaskList.
     */
    public List<Task> load() {
        File storageTasks = new File(filePath);
        if (!storageTasks.exists()) {
            try {
                File makeDataDir = new File(storageTasks.getParent());
                if (!makeDataDir.exists() && !makeDataDir.mkdirs()) {
                    System.out.println("Error in creating directory!");
                }
                if (!storageTasks.createNewFile()) {
                    System.out.println("Error in creating file!");
                }
            } catch (IOException e) {
                System.out.println("Something went wrong!");
            }
        }
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(storageTasks);
            while (s.hasNext()) {
                String str = s.nextLine();
                String[] taskDetails = str.split(" / ");
                switch (taskDetails[0]) {
                case "T":
                    if (taskDetails[1].equals("1")) {
                        tasks.add(new Todo(taskDetails[2], true));
                    } else {
                        assert taskDetails[1].equals("0") : "todo task should be marked as undone";
                        tasks.add(new Todo(taskDetails[2], false));
                    }
                    break;
                case "D":
                    if (taskDetails[1].equals("1")) {
                        tasks.add(new Deadline(taskDetails[2], true, taskDetails[3]));
                    } else {
                        assert taskDetails[1].equals("0") : "deadline task should be marked as undone";
                        tasks.add(new Deadline(taskDetails[2], false, taskDetails[3]));
                    }
                    break;
                case "E":
                    if (taskDetails[1].equals("1")) {
                        tasks.add(new Event(taskDetails[2], true, taskDetails[3], taskDetails[4]));
                    } else {
                        assert taskDetails[1].equals("0") : "event task should be marked as undone";
                        tasks.add(new Event(taskDetails[2], false, taskDetails[3], taskDetails[4]));
                    }
                    break;
                default:
                    System.out.println("Something went wrong!");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong!");
        }
        return tasks;
    }
}
