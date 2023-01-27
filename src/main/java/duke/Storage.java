package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String filePath;

    public Storage(String newFilePath) {
        filePath = newFilePath;
    }

    public ArrayList<Task> loadTasksFromTaskLog() {
        File taskLog = new File(filePath);
        Scanner taskLogScanner = null;

        try {
            taskLogScanner = new Scanner(taskLog);
        } catch (FileNotFoundException e) {
            System.out.println("duke.Task log file not found");
        }

        ArrayList<Task> tasks = new ArrayList<>();
        while (taskLogScanner.hasNextLine()) {
            String taskLogLine = taskLogScanner.nextLine();
            tasks.add(Parser.translateTaskLogToTask(taskLogLine));
        }
        return tasks;
    }

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
}
