package duke.storage;

import duke.exception.DukeException;
import duke.task.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


public class Storage {
    private String filePath;
    private FileWriter writer;
    private ArrayList<Task> taskList = new ArrayList<>();
    private Task task;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private Task convertStrToTask(String str) {
        String getTaskType = str.substring(0, 1);
        String getStatus = str.substring(4, 5);
        switch (getTaskType) {
            case "T":
                task = new Todo(str.substring(8));
                break;
            case "D":
                String updatedStr = str.substring(8).replace("(", "").replace(")", "").trim();
                String[] paraForDeadline = updatedStr.split("by: ", 2);
                System.out.println(paraForDeadline[1]);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hhmm a");
                LocalDateTime tempDueDate = LocalDateTime.parse(paraForDeadline[1], formatter);

                DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                String finalDueDate = tempDueDate.format(newFormatter);
                System.out.println(finalDueDate);

                LocalDateTime dueDate = LocalDateTime.parse(finalDueDate, newFormatter);
                task = new Deadline(paraForDeadline[0], dueDate);
                break;
            case "E":
                String updatedStrr = str.substring(8).replace("(", "").replace(")", "");
                String[] getParas = updatedStrr.split("from: ", 2);
                String getDesc = getParas[0];
                String[] getFromBy = getParas[1].split("to: ", 2);
                task = new Event(getDesc, getFromBy[0], getFromBy[1]);
                break;
            default:
                break;
        }

        if (getStatus.equals("0")) {
            task.markAsIncomplete();
        } else if (getStatus.equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    public ArrayList<Task> load() throws DukeException {

        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(filePath));
            String line = bufReader.readLine();
            while (line != null) {
                Task addTask = convertStrToTask(line);
                taskList.add(addTask);
                System.out.println(addTask);
                line = bufReader.readLine();
                System.out.println(line);
            }
            bufReader.close();
        } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        } finally {
            return taskList;
        }
    }

    public void update(Task task) throws IOException {
        Path dataDir = Paths.get("data");
        Path dataFile = Paths.get("data/tasks.txt");
        // directory does not exists
        if (!Files.isDirectory(dataDir)) {
            Files.createDirectory(dataDir);
            Files.createFile(dataFile);
        } else {
            // if directory exist, check if duke.txt exists
            if (!Files.exists(dataFile)) {
                Files.createFile(dataFile);
            }
        }
        writer = new FileWriter("data/tasks.txt", true);
        String finalTasks = "";
        String taskInfo = task.toString().replace("[ ]", " | 0 |").replace("[X]", "| 1 |");
        taskInfo = taskInfo.replace("[", "").replace("]", "");
        finalTasks = finalTasks + taskInfo + "\n";
        writer.write(finalTasks);
        writer.close();
    }
}
