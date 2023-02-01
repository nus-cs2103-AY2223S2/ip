package duke;

import exceptions.IncorrectNoOfArgumentException;

import java.io.IOException;

import java.util.ArrayList;

import java.time.format.DateTimeParseException;

/**
 * Represents the Duke application, capable of storing 3 types of Tasks ("ToDos", "Deadline" & "Event")
 * in the hard disk for users to refer to at any point of time.
 * <p></p>
 * Acts as a form of reminder / tracker and supports the following functions:
 * add, remove, mark, unmark and display list.
 * @author MrTwit99
 * @since 2023-02-01
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a Duke object that requires the filePath and folderPath to initialize the application,
     * retrieving tasks stored in the hard disk.
     *
     * @param filePath  Relative path to locate the file with the stored tasks.
     * @param folderPath Relative path to locate the directory storing the file.
     */
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

    /**
     * Executes the Duke application, requires user input to perform necessary actions and stops the Duke application
     * when user inputs the given command to end.
     * <p></p>
     * Functions currently supported by Duke: add Task, remove Task, mark Task, unmark Task, delete Task, list, bye.
     * @throws IOException On input error.
     * @see IOException
     */
    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            ArrayList<String> commandInfoList;
            try {
                commandInfoList = Parser.parse(ui.readCommand());
            } catch (IncorrectNoOfArgumentException e) {
                System.out.println(e);
                continue;
            }
            if (commandInfoList.size() != 0) {
                switch (commandInfoList.get(0)) {
                case "bye":
                    isExit = true;
                    ui.showFarewellMessage();
                    break;
                case "list":
                    ui.printCommand(tasks.list());
                    break;
                case "find":
                    ui.printCommand(tasks.find(commandInfoList.get(1)));
                    break;
                case "mark":
                    ui.printCommand(tasks.markTask(commandInfoList, this.storage));
                    break;
                case "unmark":
                    ui.printCommand(tasks.unmarkTask(commandInfoList, this.storage));
                    break;
                case "delete":
                    ui.printCommand(tasks.deleteTask(commandInfoList, this.storage));
                    break;
                case "error":
                    break;
                default:
                    try {
                        ui.printCommand(tasks.addTask(commandInfoList, this.storage));
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

    /**
     * This is the main method that creates the Duke object and boots the application up via run() method.
     *
     * @param args Input given by users via the CLI
     * @throws IOException On input error.
     * @see IOException
     */
    public static void main(String[] args) throws IOException {
        new Duke("data/storage.txt", "data").run();
    }
}