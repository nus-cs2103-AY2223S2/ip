package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    static Storage storage;
    static TaskList<Task> taskList;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
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
        Duke nero = new Duke("/Users/nicholas/Documents/NUS notes/Y2S2/CS2103/ip/Duke");
        System.out.println("Hi, I'm Nero and I am an automated chat bot" + "\n" + "What would you like to do?");
        TaskList<Task> taskList = nero.getTaskList();
        while (sc.hasNextLine()) {
            String originalString = sc.nextLine();
            try {
                taskList = parser.parseCommand(originalString, taskList);
                storage.saveFile(taskList);
            } catch (IOException e) {
                System.out.println("File not found :((");
            } catch (NeroException ne) {
                System.out.println("Error occured :((((");
            }
        }
    }
}

