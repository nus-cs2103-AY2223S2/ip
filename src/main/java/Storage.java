import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

//check if the file and directory exists
// write to file

public class Storage {
    private String filePath;
    private FileWriter writer;
    private ArrayList<Task> taskList = new ArrayList<>();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private Task convertStrToTask(String str) {
        String getTaskType = str.substring(0,0);
        String getStatus = str.substring(4,4);
        Task task = null;
        switch (getTaskType) {
            case "T":
                task = new Todo(str.substring(9));
                break;
            case "D":
                String updatedStr = str.substring(9).replace("(", "").replace(")", "");
                String[] paraForDeadline = updatedStr.split("by: ", 2);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hhmm a");
                LocalDateTime dueDate = LocalDateTime.parse(paraForDeadline[1], formatter);
                // date time format is wrong
                task = new Deadline(paraForDeadline[0], dueDate);
                break;
            case "E":
                String updatedStrr = str.substring(9).replace("(", "").replace(")", "");
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
        Path dataFile = Paths.get(filePath);
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(filePath));
            String line = bufReader.readLine();
            while (line != null) {
                convertStrToTask(line);
                line = bufReader.readLine();
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

    public void store(TaskList tasks) throws IOException {
        Path dataDir = Paths.get("ip/data");
        Path dataFile = Paths.get("ip/data/tasks.txt");
        // directory does not exists
        if (! Files.isDirectory(dataDir)) {
            Files.createDirectory(dataDir);
            Files.createFile(dataFile);
        } else {
            // if directory exist, check if duke.txt exists
            if (! Files.exists(dataFile)) {
                Files.createFile(dataFile);
            }
        }
        writer = new FileWriter("ip/data/tasks.txt", true);
        int length = tasks.getLength();
        int i = 0;
        String finalTasks = "";
        while (i < length) {
            String taskInfo = tasks.getTask(i).toString().replace("[ ]", " | 0 |").replace("[X]", "| 1 |");
            taskInfo = taskInfo.replace("[", "").replace("]", "");
            finalTasks = finalTasks + taskInfo + "\n";
            i++;
        }
        writer.write(finalTasks);
        writer.close();
    }
}
