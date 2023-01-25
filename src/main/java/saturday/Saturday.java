package saturday;

import saturday.collections.TaskList;
import saturday.command.Command;
import saturday.exceptions.SaturdayException;
import saturday.utilities.Storage;
import saturday.utilities.Ui;

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
                if (command.equals(Command.BYE)) {
                    isActive = false;
                    Ui.output("Bye. Hope to see you again soon!");
                }
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
        String filePath = Storage.getFilePath();
        new Saturday(filePath).run();
    }

}
