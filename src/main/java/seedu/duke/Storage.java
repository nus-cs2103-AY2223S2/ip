package seedu.duke;

import seedu.duke.tasks.*;

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

    /**
     *  Constructor for Storage
     *
     *  @param fileName String of the name of the save file
     */
    Storage(String fileName) {
        String currPath = System.getProperty("user.dir");
        this.path = Paths.get(currPath, "src", "data", fileName);
    }

    /**
     *  Coverts the text in save file into a TaskList object
     *
     *  @return TaskList object of the save file
     */
    public TaskList readFile() throws DukeException {
        TaskList data = new TaskList();
        try {
            File file = new File(String.valueOf(this.path));
            Scanner sc = new Scanner(file);
            // scan and read each line on the duke.txt file
            // need to be able to have a common function that creates the different types of tasks
            while (sc.hasNextLine()) {
                String[] taskDetails = sc.nextLine().split("\\|");
                String taskType = taskDetails[0];
                boolean isDone = Boolean.parseBoolean(taskDetails[1]);
                switch (taskType) {
                case "T":
                    data = data.addTask(new Todo(taskDetails[2], isDone, taskType));
                    break;
                case "D":
                    String deadline = taskDetails[3];
                    LocalDateTime formattedDeadline = formatTimeStamp(deadline);
                    data = data.addTask(new Deadline(taskDetails[2], isDone, taskType, formattedDeadline));
                    break;
                case "E":
                    String from = taskDetails[3];
                    LocalDateTime formattedFrom = formatTimeStamp(from);
                    String to = taskDetails[4];
                    LocalDateTime formattedTo = formatTimeStamp(to);
                    data = data.addTask(new Event(taskDetails[2], isDone, taskType, formattedFrom, formattedTo));
                    break;
                }
            }
        } catch (FileNotFoundException err) {
            throw new DukeException("Save file does not exist!");
        }
        return data;
    }

    /**
     *  Formats the String of the LocalDateTime time stamp into a LocalDateTime object
     *
     *  @param timeStamp String of the LocalDateTime time stamp
     *  @return LocalDateTime object of the time stamp
     */
    private LocalDateTime formatTimeStamp(String timeStamp) {
        String[] timeStampDetails = timeStamp.split("T");
        LocalDate date = LocalDate.parse(timeStampDetails[0]);
        LocalTime time = LocalTime.parse(timeStampDetails[1]);
        return LocalDateTime.of(date, time);
    }

    /**
     *  Coverts the TaskList object into a String for the save file
     *
     *  @param taskList TasKList object to be saved into save file
     */
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
