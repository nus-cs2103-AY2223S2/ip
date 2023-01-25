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

    private static String DEFAULT_PATH = "data/duke.txt";
    private Path filePath;
    private String currRelativeFilePath;

    public Storage() {
        try {
            currRelativeFilePath = new File(".").getCanonicalPath();
            filePath = Paths.get(currRelativeFilePath + '/' + DEFAULT_PATH);
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public List<Task> loadData() {
        List<Task> list = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String i: lines) {
                char type = i.charAt(1);
                Task task = null;
                if (type == 'T') {
                    String desc = i.substring(7);
                    task = new Todo(desc);

                } else if (type == 'D') {
                    String substring = i.substring(7);
                    String segments[] = substring.split("/", 2);
                    String date = segments[1].split(" ", 2)[1];
                    String desc = segments[0];
                    task = new Deadline(date, desc);

                } else if (type == 'E') {
                    String segments[] = i.split("/", 3);
                    String start = segments[segments.length - 2].split(" ", 2)[1];
                    String end = segments[segments.length - 1].split(" ", 2)[1];
                    String subSegments[] = segments[0].split(" ", 2);
                    String desc = subSegments[1];
                    task = new Event(start, end, desc);
                }
                list.add(task);
            }

        } catch (IOException e) {
            System.out.println(e);

        } finally {
            return list;
        }
    }

    public void save(TaskList taskList) {
        String txt = "";
        for (int i = 0; i < taskList.getNumTasks(); i++) {
            txt += taskList.getTask(i) + "\n";
        }
        try {
            FileWriter fileWriter = new FileWriter(DEFAULT_PATH);
            fileWriter.write(txt);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
