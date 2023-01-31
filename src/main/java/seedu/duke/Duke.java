/**
 * Project name: Duke
 * @author Tan Jun Da A023489eU
 */

package seedu.duke;

/**
 * Represents the main program of Duke.
 */
public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Represents the set of commands by the user.
     */
    protected enum Commands {
        bye,
        mark,
        unmark,
        list,
        todo,
        deadline,
        event,
        delete,
        find
    }

    /**
     * Constructor for the Duke class.
     *
     * @param filepath The file path for the data stored in the txt file.
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * The start of the program.
     */
    public String run(String input) {
        // Description of the task.
        String description = "";
        // Return of the final String to add.
        String dukeText = "";

        description = "";
        Parser userParse = new Parser(input);
        try {
            Commands userCommand = userParse.checkCommand(userParse.command);
            switch(userCommand) {
            case bye:
                dukeText += ui.bye();
                break;
            case mark:
                dukeText += ui.markDisplay(tasks.mark(userParse), userParse);
                break;
            case unmark:
                dukeText += ui.unmarkDisplay(tasks.unmark(userParse), userParse);
                break;
            case list:
                dukeText += "Here are the list of tasks:\n";
                dukeText += ui.list(tasks);
                break;
            case todo:
                tasks.addTodo(description, userParse);
                dukeText += ui.addTodoMessage(tasks);
                break;
            case deadline:
                tasks.addDeadline(description, userParse);
                dukeText += ui.addDeadlineMessage(tasks);
                break;
            case event:
                tasks.addEvent(description, userParse);
                dukeText += ui.addEventMessage(tasks);
                break;
            case delete:
                dukeText += ui.deleteMessage(tasks, tasks.delete(userParse));
                break;
            case find:
                dukeText += ui.findMessage(tasks.find(userParse));
                break;
            default:
                break;
            }
            storage.write(tasks);
        } catch (DukeException e) {
            dukeText += e.getMessage();
        }
        return dukeText;
    }

    /**
     * Returns the duke response.
     *
     * @param input The input from the user.
     * @return The response of Duke.
     */
    protected String getResponse(String input) {
        Duke currDuke = new Duke("data/duke.txt");
        return "Duke: \n" + currDuke.run(input);
    }

    /**
     * Returns the list of Commands.
     *
     * @return A String of List of commands.
     */
    public static String getCommands() {
        String commandList = "";
        for (Commands curr : Commands.values()) {
            commandList += curr.name() + "\n";
        }
        return commandList;
    }
}
