package willy.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import willy.task.Deadline;
import willy.task.Event;
import willy.task.Task;
import willy.task.TaskList;
import willy.task.Todo;

/**
 * Represents the storage 
 */
public class Storage {
    private static final String DATA_PATH = "data/Willy.txt";
    private Path filePath;
    private String relativeFilePath;

    /**
     * Creates the storage with the given default location
     */
    public Storage() {
        try {
            relativeFilePath = new File(".").getCanonicalPath();
            filePath = Paths.get(relativeFilePath, '/' + DATA_PATH);
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Creats a custom storage based on the param file location
     * @param filePathGiven
     */
    public Storage(String filePathGiven) {
        // double check later
        try {
            relativeFilePath = new File(".").getCanonicalPath();
            filePath = Paths.get(relativeFilePath, '/' + filePathGiven);
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    /** 
     * To load the data from the storage location text file and return as a list of tasks
     * @return List<Task>
     */
    public List<Task> loadData() {
        List<Task> list = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                char taskType = line.charAt(1);
                Task taskEntry = null;
                if (taskType == 'T') {
                    // [T][ ]todo borrow book
                    String details = line.substring(6);
                    taskEntry = new Todo(details);

                } else if (taskType == 'D') {
                    // [D][ ]deadline return book (by Sunday)
                    String[] result = line.split("\\(by ", 2);
                    String details = result[0].substring(6).trim();
                    String date = result[1].replace(")", "");
                    taskEntry = new Deadline(details, date);

                } else if (taskType == 'E') {
                    // [E][ ]event project meeting (from Mon 2pm to 4pm)
                    String[] result = line.split("\\(from ", 2);
                    String details = result[0].substring(6).trim();
                    String fromAndToResult = result[1].replace(")", "");
                    String[] fromAndToSplit = fromAndToResult.split(" to ");
                    String from = fromAndToSplit[0];
                    String to = fromAndToSplit[1];
                    taskEntry = new Event(details, from, to);
                }
                list.add(taskEntry);
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            return list;
        }
    }

    /**
     * To save the current tasklist into textfile location storage
     * @param tList
     */
    public void save(TaskList tList) {
        String tempText = "";
        for (int i = 0; i < tList.getTaskCount(); i++) {
            tempText += tList.getTask(i) + "\n";
        }

        try {
            FileWriter fileWriter = new FileWriter(DATA_PATH); // May need to change this
            fileWriter.write(tempText);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
