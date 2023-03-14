package duke;

import task.DeadlineTask;
import task.DurationTask;
import task.EventTask;
import task.Task;
import task.TaskList;
import task.TodoTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Storage class handles the interaction between local hard drive and Duke.
 */
public class Storage {
    File file;
    private final static String SAVED_PATH = "data/tasks.txt";

    /**
     * Constructor for Storage instance.
     * @param filePath Path to locate file relative to project root.
     */
    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Method to write current TaskList onto file on hard drive.
     * @param taskList TaskList with tasks in Duke.
     * @throws IOException Thrown when file system encounters an error.
     * @throws DukeException Thrown when unexpected behaviour occurs with Duke.
     */
    public void save(TaskList taskList) throws IOException, DukeException {
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                throw new DukeException("Directories can't be made? Or did something go wrong...");
            }
        }
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new DukeException("This file both doesn't exist and cannot be made D:");
            }
        }
        FileWriter fileWriter = new FileWriter(file);
        for (Task task : taskList.asList()) {
            fileWriter.write(task.toString());
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }

    /**
     * Retrieves data from a previous session of Duke, if it exists.
     * @return An array of Tasks.
     * @throws DukeException Thrown when unexpected behaviour occurs with Duke.
     */
    public Task[] load() throws DukeException {
        File f = new File(SAVED_PATH);
        try {
            if (!f.exists()) {
                Ui.showNewUserMessage();
                return new Task[0];
            } else {
                Ui.showWelcomeBackMessage();
                Scanner scanner = new Scanner(f);
                ArrayList<Task> taskList = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    handleNextLine(scanner, taskList);
                }
                displayLoadedList(taskList);
                return taskList.toArray(new Task[0]);
            }
        } catch (IOException io) {
            throw new DukeException("Something went wrong while trying to load...");
        }
    }

    private void handleNextLine(Scanner scanner, ArrayList<Task> taskList) {
        Task task;
        String currTask = scanner.nextLine();
        String taskType = currTask.split(" ")[0];
        String taskCompletion = currTask.substring(4,7);
        Boolean isComplete = Objects.equals(taskCompletion, "[X]");
        String taskDetails;
        taskDetails = setMarkOnTask(currTask, isComplete);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM uuuu");
        switch (taskType) {
        case "[T]":
            task = new TodoTask(taskDetails, isComplete);
            break;
        case "[D]":
            task = generateDeadlineTask(taskDetails, formatter, isComplete);
            break;
        case "[E]":
            task = generateEventTask(taskDetails, formatter, isComplete);
            break;
        case "[Du]":
            task = generateDurationTask(taskDetails, isComplete);
            break;
        default:
            task = new Task(taskDetails);
        }
        taskList.add(task);
    }

    private String setMarkOnTask(String currTask, Boolean isComplete) {
        if (isComplete) {
            return currTask.split(" \\[X] ")[1];
        } else {
            return currTask.split(" \\[ ] ")[1];
        }
    }

    private Task generateDeadlineTask(String taskDetails
            , DateTimeFormatter formatter, Boolean isComplete) {
        String deadlineName = taskDetails.split(" \\(by: ")[0];
        String date = taskDetails.split(" \\(by: ")[1].split("\\)")[0];
        LocalDate deadLine = LocalDate.parse(date, formatter);
        return new DeadlineTask(deadlineName, deadLine, isComplete);
    }

    private Task generateEventTask(String taskDetails
            , DateTimeFormatter formatter, Boolean isComplete) {
        String eventName = taskDetails.split(" \\(from:")[0];
        String eventPeriod = taskDetails.split("\\(from: ")[1];
        String startStr = eventPeriod.split(" to: ")[0];
        String endStr = eventPeriod.split(" to: ")[1].split("\\)")[0];
        LocalDate start = LocalDate.parse(startStr, formatter);
        LocalDate end = LocalDate.parse(endStr, formatter);
        return new EventTask(eventName, start, end, isComplete);
    }

    private Task generateDurationTask(String taskDetails, Boolean isComplete) {
        String durationName = taskDetails.split(" \\( ")[0];
        String durationTime = taskDetails.split(" \\( ")[1];
        int hours = Integer.parseInt(durationTime.split(" hours")[0]);
        String minsStr = durationTime.split(" hours ")[1];
        int mins = Integer.parseInt(minsStr.split(" mins ")[0]);
        return new DurationTask(durationName, hours, mins, isComplete);
    }

    public void displayLoadedList(ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i += 1) {
            int currItem = i + 1;
            System.out.println(currItem + ": " + taskList.get(i));
        }
        Ui.showWelcomePrompt();
    }
}
