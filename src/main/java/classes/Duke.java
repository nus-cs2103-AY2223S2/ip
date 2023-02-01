package classes;

import exceptions.IncorrectNoOfArgumentException;

import java.io.IOException;

import java.util.ArrayList;

import java.time.format.DateTimeParseException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath, String folderPath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, folderPath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            this.ui.showLoadingError(e);
            this.tasks = new TaskList(new ArrayList<String>());
        }
    }

    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            ArrayList<String> commandInfo;
            try {
                commandInfo = Parser.parse(ui.readCommand());
            } catch (IncorrectNoOfArgumentException e) {
                System.out.println(e);
                continue;
            }
            if (commandInfo.size() != 0) {
                switch (commandInfo.get(0)) {
                case "bye":
                    isExit = true;
                    ui.showFarewellMessage();
                    break;
                case "list":
                    ui.printCommand(tasks.list());
                    break;
                case "mark":
                    ui.printCommand(tasks.markTask(commandInfo, this.storage));
                    break;
                case "unmark":
                    ui.printCommand(tasks.unmarkTask(commandInfo, this.storage));
                    break;
                case "delete":
                    ui.printCommand(tasks.deleteTask(commandInfo, this.storage));
                    break;
                case "error":
                    break;
                default:
                    try {
                        ui.printCommand(tasks.addTask(commandInfo, this.storage));
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid inputs!\n");
                        System.out.println("Please enter your date & time in the format: YYYY-MM-DD HH:MM \n");
                        System.out.println("Please also ensure they are valid values!\n");
                        break;
                    }
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/storage.txt", "data").run();
    }
}