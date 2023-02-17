package seedu.duke;

import seedu.duke.tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Storage {

    private static final int DESCRIPTION_INDEX = 2;
    private static final int BY_INDEX = 3;
    private static final int FROM_INDEX = 3;
    private static final int TO_INDEX = 4;
    private final String path;

    /**
     *  Constructor for Storage
     *
     *  @param fileName String of the name of the save file
     */
    Storage(String fileName) {
        String absolutePath = new File("").getAbsolutePath() + "/data/" + fileName;
        File file = new File( absolutePath );
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        this.path = absolutePath;
    }

    private TaskList handleTodo(TaskList data, String[] taskDetails, boolean isDone, String taskType) {
        return data.addTask(new Todo(taskDetails[DESCRIPTION_INDEX], isDone, taskType));
    }

    private TaskList handleDeadline(TaskList data, String[] taskDetails, boolean isDone, String taskType) {
        String deadline = taskDetails[BY_INDEX];
        LocalDateTime formattedDeadline = formatTimeStamp(deadline);
        return data.addTask(new Deadline(taskDetails[DESCRIPTION_INDEX], isDone, taskType, formattedDeadline));
    }

    private TaskList handleEvent(TaskList data, String[] taskDetails, boolean isDone, String taskType) {
        String from = taskDetails[FROM_INDEX];
        LocalDateTime formattedFrom = formatTimeStamp(from);
        String to = taskDetails[TO_INDEX];
        LocalDateTime formattedTo = formatTimeStamp(to);
        return data.addTask(new Event(taskDetails[DESCRIPTION_INDEX], isDone, taskType, formattedFrom, formattedTo));
    }

    /**
     *  Coverts the text in save file into a TaskList object
     *
     *  @return TaskList object of the save file
     */
    public TaskList readFile() throws DukeException {
        TaskList data = new TaskList();
        File file = new File(this.path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner sc = new Scanner(file);
            // scan and read each line on the duke.txt file
            // need to be able to have a common function that creates the different types of tasks
            while (sc.hasNextLine()) {
                String[] taskDetails = sc.nextLine().split("\\|");
                String taskType = taskDetails[0];
                boolean isDone = Boolean.parseBoolean(taskDetails[1]);
                switch (taskType) {
                case "T":
                    data = handleTodo(data, taskDetails, isDone, taskType);
                    break;
                case "D":
                    data = handleDeadline(data, taskDetails, isDone, taskType);
                    break;
                case "E":
                    data = handleEvent(data, taskDetails, isDone, taskType);
                    break;
                default:
                    throw new DukeException("Type of task detected is not registered!");
                }
            }
        } catch (FileNotFoundException err) {
            throw new DukeException("Save file does not exist!");
        } catch (IOException err) {
            System.out.println(err);
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
