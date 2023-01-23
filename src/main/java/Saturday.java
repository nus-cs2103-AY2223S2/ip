import collections.TaskList;
import exceptions.SaturdayException;
import models.Task;
import utilities.UI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Saturday {
    private static boolean isActive = true;
    private static Storage storage;
    private static TaskList taskList;

    public Saturday(String filePath) {
        this.isActive = true;
        this.storage = new Storage(filePath);
        this.taskList = storage.loadTaskList();
    }

    public void run() {
        UI.greet();

        Scanner scanner = new Scanner(System.in);
        while (isActive) {
            String input = scanner.nextLine();
            UI.divider();
            try {
                Command command = Command.getCommand(input);
                command.execute(input);
                storage.saveTaskList(taskList);
            } catch (SaturdayException e) {
                UI.output(e.getMessage());
            }
            UI.divider();
            UI.newline();
        }
    }
    public static void main(String[] args) {
        Path dataDirPath = Paths.get(System.getProperty("user.dir"), "data");
        if (!Files.exists(dataDirPath)) {
            try {
                Files.createDirectory(dataDirPath);
            } catch (IOException e) {
                UI.output(e.getMessage());
            }
        }
        Path filePath = Paths.get(System.getProperty("user.dir"), "data", "task_list.txt");
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                UI.output(e.getMessage());
            }
        }
        new Saturday(filePath.toString()).run();
    }

    public static void addToTaskList(Task task) {
        taskList.add(task);
        UI.output("Got it. I've added this task:\n\t " + task + "\n\tNow you have " + taskList.size() + " tasks in the list.");
    }

    public static void markTaskList(int i) {
        taskList.mark(i);
    }

    public static void unMarkTaskList(int i) {
        taskList.unMark(i);
    }

    public static Task getTask(int i) {
        return taskList.get(i);
    }

    public static int getTaskListSize() {
        return taskList.size();
    }

    public static Task removeTask(int i) {
        return taskList.remove(i);
    }

    public static void displayList() {
        UI.output("Here are the tasks in your list:\n\t" + taskList.toString());
    }

    public static void exit() {
        isActive = false;
        UI.output("Bye. Hope to see you again soon!");
    }

}
