package duke;

import java.util.Scanner;

/**
 * The sequence of events for the chat-bot duke.
 */
public class Duke {

    /** Scanner object use to receive user inputs */
    private Scanner getInput;

    /** Ui object to handle interactions with user */
    private Ui ui;

    /** Storage object to deal with file operations */
    private Storage storage;

    /** List to store all task */
    private TaskList taskList;

    private Duke(String filePath) {
        this.getInput = new Scanner(System.in);
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.loadFile());

        ui.printIntro();
        ui.printList(taskList);
    }

    private String askForInput() {
        System.out.print("> ");
        return getInput.nextLine();
    }

    private void run() {
        // Execute inputs
        String userInput;
        EventType curEvent;
        loop: while (true) {

            // Get inputs
            userInput = askForInput();

            // Check if valid
            try {
                curEvent = Parser.parse(userInput);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                continue; // skip the rest of the code and go to next iteration
            }

            // If valid keyword, execute input
            switch (curEvent) {
                case BYE:
                    break loop;
                case LIST:
                    ui.printListWithAttitude(taskList);
                    break;
                case MARK:
                    taskList.markEvent(userInput);
                    storage.overwriteFile(taskList);
                    break;
                case UNMARK:
                    taskList.unmarkEvent(userInput);
                    storage.overwriteFile(taskList);
                    break;
                case DELETE:
                    taskList.deleteEvent(userInput);
                    storage.overwriteFile(taskList);
                    break;
                case TODO:
                    taskList.addToDoEvent(userInput);
                    storage.overwriteFile(taskList);
                    break;
                case DEADLINE:
                    taskList.addDeadlineEvent(userInput);
                    storage.overwriteFile(taskList);
                    break;
                case EVENT:
                    taskList.addEventEvent(userInput);
                    storage.overwriteFile(taskList);
                    break;
            }
        }
        ui.printOutro();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
