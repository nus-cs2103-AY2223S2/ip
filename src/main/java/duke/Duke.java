package duke;

import java.util.Scanner;

/**
 * Class for Duke, a Personal Assistant Chatbot
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static boolean isExit = false;

    /**
     * Constructor for Duke Class
     * @param filePath File path to task log
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasksFromTaskLog());
    }

    /**
     * Runs Duke interface which interprets user input
     */
    public void run() {
        ui.greetUser();
        while (!isExit) {
            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine();
            String userCommand = Parser.getCommand(userInput);
            try {
                switch (userCommand) {
                case "bye":
                    ui.sayGoodbye();
                    return;
                case "list":
                    ui.printTasks(tasks);
                    break;
                case "delete":
                    int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    ui.informDeletion(tasks.getTask(taskIndex), tasks.getSize());
                    tasks.deleteTask(taskIndex);
                    Storage.saveTasksToTaskLog(tasks);
                    break;
                case "mark":
                    int toMark = userInput.charAt(5) - 48;
                    Task toMarkTask = tasks.getTask(toMark - 1);
                    toMarkTask.markTask();
                    ui.informTaskIsMarked(toMarkTask);
                    Storage.saveTasksToTaskLog(tasks);
                    break;
                case "unmark":
                    int toUnMark = userInput.charAt(7) - 48;
                    Task toUnMarkTask = tasks.getTask(toUnMark - 1);
                    toUnMarkTask.unmarkTask();
                    ui.informTaskIsUnMarked(toUnMarkTask);
                    Storage.saveTasksToTaskLog(tasks);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    Task newTask = Parser.translateUserInputToTask(userInput);
                    tasks.addTask(newTask);
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n\n");
                }
            } catch (DukeException e) {
                this.ui.showError(e);
            }
        }
    }

    /**
     * Initialise Duke chatbot
     */
    public static void main(String[] args) throws DukeException {
        new Duke("./data/Duke.txt").run();
    }
}
