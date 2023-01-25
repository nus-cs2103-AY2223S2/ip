import collections.TaskList;
import exceptions.SaturdayException;
import utilities.Storage;
import utilities.Ui;

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
        Ui.greet();

        Scanner scanner = new Scanner(System.in);
        while (isActive) {
            String input = scanner.nextLine();
            Ui.divider();
            try {
                Command command = Command.getCommand(input);
                command.execute(taskList, input);
                storage.saveTaskList(taskList);
            } catch (SaturdayException e) {
                Ui.output(e.getMessage());
            }
            Ui.divider();
            Ui.newline();
        }
    }
    public static void main(String[] args) {
        Path dataDirPath = Paths.get(System.getProperty("user.dir"), "data");
        if (!Files.exists(dataDirPath)) {
            try {
                Files.createDirectory(dataDirPath);
            } catch (IOException e) {
                Ui.output(e.getMessage());
            }
        }
        Path filePath = Paths.get(System.getProperty("user.dir"), "data", "task_list.txt");
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                Ui.output(e.getMessage());
            }
        }
        new Saturday(filePath.toString()).run();
    }

    public static void exit() {
        isActive = false;
        Ui.output("Bye. Hope to see you again soon!");
    }

}
