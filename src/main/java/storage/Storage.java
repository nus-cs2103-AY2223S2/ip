package storage;

import task.Deadline;
import task.Event;
import task.Todo;
import task.Task;
import task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static String DATA_PATH = "data/Willy.txt";
    private Path filePath;
    private String relativeFilePath;

    public Storage() {
        try {
            relativeFilePath = new File(".").getCanonicalPath();
            filePath = Paths.get(relativeFilePath, '/' + DATA_PATH);
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Storage(String filePathGiven) {
        // double check later
        try {
            relativeFilePath = new File(".").getCanonicalPath();
            filePath = Paths.get(relativeFilePath, '/' + DATA_PATH);
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

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

    public void save(TaskList tList) {
        String tempText = "";
        for (int i = 0; i < tList.getTaskCount(); i++) {
            tempText += tList.getTask(i) + "\n";
        }

        try {
            FileWriter fileWriter = new FileWriter(DATA_PATH);
            fileWriter.write(tempText);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
