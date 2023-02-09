package duke;

import duke.command.Parser;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for Storage
 */
public class Storage {
    private static String filePath;

    /**
     * Constructor for Storage
     * @param newFilePath File path to task log
     */
    public Storage(String newFilePath) {
        filePath = newFilePath;
    }

    /**
     * Loads tasks from task log file
     * @return An ArrayList of Tasks from task log
     */
    public ArrayList<Task> loadTasksFromTaskLog() {
        File taskLog = new File(filePath);
        Scanner taskLogScanner = null;
        try {
            taskLogScanner = new Scanner(taskLog);
        } catch (FileNotFoundException e) {
            System.out.println("Task log file not found");
        }
        ArrayList<Task> tasks = new ArrayList<>();
        while (taskLogScanner.hasNextLine()) {
            String taskLogLine = taskLogScanner.nextLine();
            tasks.add(Parser.translateTaskLogToTask(taskLogLine));
        }
        return tasks;
    }

    /**
     * Save tasks to task log file
     * @param taskList TaskList to be saved into task log file
     */
    public static void saveTasksToTaskLog(TaskList taskList) {
        try {
            FileWriter taskLog = new FileWriter(filePath);
            for (Task i : taskList.getTasks()) {
                taskLog.write(i.toLog());
            }
            taskLog.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void logFileExists(String filePath) {
        File logFile = new File(filePath);
        try {
            if (!logFile.exists()) {
                logFile.getParentFile().mkdir();
                logFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Log file cannot be created");
        }
    }
}
