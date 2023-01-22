package files;

import parsers.TaskInfoParser;
import tasks.Task;
import tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {

    final static String BANNER = "____________________________________________________________";

    public static TaskList loadData(TaskList taskList, String filePath) throws IOException {
        try {
            taskList = readFromFile(filePath);
            System.out.println("Hrmm Hrmm, some past tasks I see!!\n'list' command to see more, you must enter");
            System.out.println(BANNER);
            return taskList;
        } catch (FileNotFoundException e) {
            Path path = Paths.get("src/main/data");
            Files.createDirectories(path);
            File newTaskFile = new File(filePath);
            newTaskFile.createNewFile();
            System.out.println("A new file created, I have!");
        }
        return new TaskList();
    }

    public static TaskList readFromFile(String path) throws FileNotFoundException {
        TaskList taskList = new TaskList();
        File file = new File(path);
        Scanner fileScanner = new Scanner(file);
        while(fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] commandArray = line.trim().split(" ");
            Task task = TaskInfoParser.parse(commandArray);
            taskList.addTaskSilent(task);
        }
        fileScanner.close();
        return taskList;
    }

    public static void saveData(String filepath, TaskList taskList) {
        DukeFileWriter.writeToFile(filepath, taskList);
    }
}
