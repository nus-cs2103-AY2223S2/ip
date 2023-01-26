package duke;

import duke.exceptions.DukeException;

/**
 * The main driver class for Duke chatbot. This bot handles high level logic for the bot including startup and
 * shutdown.
 */
public class Duke {
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;
    private final Parser parser;

    /**
     * Primary constructor for an instance of the chatbot. This initialises the Ui, taskList, storage and parser
     * instances used by the bot, which are not to be modified throughout the lifecycle of the bot.
     * @param dataStoragePath A string representing the path which the bot's data should be stored on disk
     */
    Duke(String dataStoragePath) {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(dataStoragePath, this.taskList);
        this.parser = new Parser();
    }

    /**
     * Terminates the current instance of the chatbot by exiting the program.
     */
    void exit() {
        storage.updateData(this.taskList);
        ui.displayMessage("Bye. Hope to see you again soon!\n");
        System.exit(0);
    }

    /**
     * Starts an instance of the chatbot by displaying welcome message and beginning to listen for user input.
     */
    void run() {
        ui.welcomeMessage();
        while (true) {
            String[] tokens = parser.parseUserInput();
            if (tokens.length == 1 && tokens[0].equals("bye")) {
                exit();
                break;
            } else if (tokens.length == 1 && tokens[0].equals("list")) {
                ui.displayItemList(taskList);
            } else if (tokens[0].equals("mark")) {
                taskList.markListItem(tokens, ui);
            } else if (tokens[0].equals("unmark")) {
                taskList.unmarkListItem(tokens, ui);
            } else if (tokens[0].equals("todo")) {
                try {
                    taskList.addToDo(tokens, ui);
                } catch (DukeException e) {
                    ui.displayMessage(e.getMessage());
                }
            } else if (tokens[0].equals("deadline")) {
                try {
                    taskList.addDeadline(tokens, ui);
                } catch (DukeException e) {
                    ui.displayMessage(e.getMessage());
                }
            } else if (tokens[0].equals("event")) {
                try {
                    taskList.addEvent(tokens, ui);
                } catch (DukeException e) {
                    ui.displayMessage(e.getMessage());
                }
            } else if (tokens[0].equals("delete")) {
                try {
                    taskList.deleteItem(tokens, ui);
                } catch (DukeException e) {
                    ui.displayMessage(e.getMessage());
                }
            } else {
                ui.displayMessage("unknown command\n");
            }
        }
    }

    public static void main(String[] args) {
        new Duke("src/data/duke.txt").run();
    }
}
