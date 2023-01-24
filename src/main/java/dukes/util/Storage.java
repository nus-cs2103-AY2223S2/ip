package dukes.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import dukes.util.*;
import dukes.task.*;
import dukes.command.*;

import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DukeException {
        try {
            File f = new File(filePath);
            Scanner fileScan = new Scanner(f);
            List<Task> taskList = new ArrayList<>();
            while (fileScan.hasNext()) {
                Task currTask = Parser.fetchTask(fileScan.nextLine());
                taskList.add(currTask);
            }
            fileScan.close();
            return taskList;
        } catch (FileNotFoundException ex) {
            throw new DukeException("Sorry, your file is missing at " + filePath);
        }
    }

    public String generateTaskList(TaskList tasks) {
        List<Task> taskList = tasks.getTaskList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            sb.append(currTask.getTag()).append("@");
            sb.append((currTask.hasDone()) ? "1" : "0").append("@");
            sb.append(currTask.getTaskName());
            if (currTask.getTag().equals("D")) {
                // Here by default use pattern yyyy-MM-dd
                sb.append("@").append(currTask.getDeadLine());
            } else if (currTask.getTag().equals("E")) {
                sb.append("@").append(currTask.getStart()).
                        append("@").append(currTask.getEnd());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void save(TaskList tasks) throws DukeException {
        String currWriteIn = generateTaskList(tasks);
        File directory = new File("data/");
        // This is a bit hard-coding...
        if (!directory.exists()) {
            directory.mkdir();
        }
        File writeFile = new File(filePath);
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(currWriteIn);
            fw.close();
        } catch(IOException ex) {
            throw new DukeException("Sorry, your file is missing! " + ex.getMessage());
        }
    }
}
