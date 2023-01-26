package seedu.duke;

import seedu.duke.Tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Storage {
    private final Path path;

    Storage(String fileName) {
        String currPath = System.getProperty("user.dir");
        this.path = Paths.get(currPath, "src", "data", fileName);
    }

    public TaskList readFile() throws DukeException {
        TaskList data = new TaskList();
        try {
            File file = new File(String.valueOf(this.path));
            Scanner sc = new Scanner(file);
            // scan and read each line on the duke.txt file
            // need to be able to have a common function that creates the different types of tasks
            while (sc.hasNextLine()) {
                String[] taskArr = sc.nextLine().split("\\|");
                String taskType = taskArr[0];
                boolean isDone = Boolean.parseBoolean(taskArr[1]);
                switch (taskType) {
                case "T":
                    data.addTask(new Todo(taskArr[2], isDone, taskType));
                    break;
                case "D":
                    String deadline = taskArr[3];
                    LocalDateTime formattedDeadline = formatTimeStamp(deadline);
                    data.addTask(new Deadline(taskArr[2], isDone, taskType, formattedDeadline));
                    break;
                case "E":
                    String from = taskArr[3];
                    LocalDateTime formattedFrom = formatTimeStamp(from);
                    String to = taskArr[4];
                    LocalDateTime formattedTo = formatTimeStamp(to);
                    data.addTask(new Event(taskArr[2], isDone, taskType, formattedFrom, formattedTo));
                    break;
                }
            }
        } catch (FileNotFoundException err) {
            throw new DukeException("Save file does not exist!");
        }
        return data;
    }

    private LocalDateTime formatTimeStamp(String timeStamp) {
        String[] timeStampArr = timeStamp.split("T");
        LocalDate date = LocalDate.parse(timeStampArr[0]);
        LocalTime time = LocalTime.parse(timeStampArr[1]);
        return LocalDateTime.of(date, time);
    }

    public void writeFile(TaskList taskList) {
        StringBuilder taskString = new StringBuilder();
        for (int i = 0; i < taskList.getSize(); i++) {
            String formattedTask = taskList.formatTask(i);
            taskString.append(formattedTask);
            taskString.append("\n");
        }
        try {
            FileWriter fileWriter = new FileWriter(String.valueOf(this.path));
            fileWriter.write(taskString.toString());
            fileWriter.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
