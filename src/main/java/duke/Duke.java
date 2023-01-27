package duke;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    public static final String DIRECTORY_PATH = "data";
    public static final String FILE_PATH = "data/duke.txt";
    Storage storage;
    TaskList<Task> taskList;

    public Duke(String filePath, String directoryPath) {
        this.storage = new Storage(filePath, directoryPath);
        try {
            this.taskList = storage.readFile();
        } catch (NeroException e) {
            this.taskList = new TaskList<Task>();
        }
    }

    public TaskList<Task> getTaskList() {
        return taskList;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        Duke nero = new Duke(FILE_PATH, DIRECTORY_PATH);
        System.out.println("Hi, I'm Nero and I am an automated chat bot" + "\n" + "What would you like to do?");
        TaskList<Task> taskList = nero.getTaskList();
        Storage storage = nero.storage;
        boolean toEnd = false;
        while (!toEnd) {
            String originalString = sc.nextLine();
            try {
                toEnd = parser.parseCommand(originalString, taskList);
                storage.saveFile(taskList);
            } catch (IOException e) {
                System.out.println("File not found :((");
            } catch (NeroException ne) {
                ne.printStackTrace();
            }
        }
    }
}

