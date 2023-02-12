package duke;

import task.Features;
import task.TaskList;

import java.util.Scanner;

/**
 * The sequence of events for the chat-bot duke.
 */
public class Duke {

    /** Scanner object use to receive user inputs */
    private Scanner getInput;

    /** UiOld object to handle interactions with user */
    private UiOld uiOld;

    /** Storage object to deal with file operations */
    private Storage storage;

    /** List to store all task */
    private TaskList taskList;



    Duke(String filePath) {
        this.getInput = new Scanner(System.in);
        this.uiOld = new UiOld();
        this.storage = new Storage(filePath);
        taskList = new TaskList(storage.loadFile());

        uiOld.printIntro();
        uiOld.printList(taskList);
    }

    private String askForInput() {
        System.out.print("> ");
        return getInput.nextLine();
    }

    private void run() {
        // Execute inputs
        String userInput;
        Features curEvent;
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
                    uiOld.printListWithAttitude(taskList);
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
                case FIND:
                    taskList.find(userInput);
            }
        }
        uiOld.printOutro();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public String getResponse(String userInput) {
        return decodeInput(userInput);
    }

    public String decodeInput(String userInput) {
        Parser parser = new Parser(this.taskList);
        try {
            return parser.decode(userInput).execute();
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
