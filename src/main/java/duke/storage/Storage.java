package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Storage {

    private static final String DEFAULT_PATH = "data/duke.txt";
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
            for (String i : lines) {
                char type = i.charAt(1);
                Task task = null;
                if (type == 'T') {
                    String desc = i.substring(7);
                    task = new Todo(desc);

                } else if (type == 'D') {
                    String sub = i.substring(7);
                    int openBraceIndex = sub.indexOf('(');
                    int closeBraceIndex = sub.indexOf(')');
                    String date = sub.substring(openBraceIndex + 5, closeBraceIndex);
                    LocalDate ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd MMM yyyy"));
                    String desc = sub.substring(0, openBraceIndex - 1);
                    task = new Deadline(ld, desc);

                } else if (type == 'E') {
                    String sub = i.substring(7);
                    String[] segments = sub.split("from: ", 2);
                    String desc = segments[0].substring(0, segments[0].length() - 2);
                    String[] dateTime = segments[1].split(" to: ", 2);
                    String start = dateTime[0];
                    String end = dateTime[1].substring(0, dateTime[1].length() - 1);
                    LocalDate sld = LocalDate.parse(start, DateTimeFormatter.ofPattern("dd MMM yyyy"));
                    LocalDate eld = LocalDate.parse(end, DateTimeFormatter.ofPattern("dd MMM yyyy"));
                    task = new Event(sld, eld, desc);
                }
                if (i.charAt(4) == 'X') {
                    task.mark();
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
