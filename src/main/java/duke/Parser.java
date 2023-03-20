package duke;


import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * A Parser has the methods that decode the requests entered by the user and executes the relevant methods that
 * facilitate the requests of the user
 */
public class Parser {
    private final Storage storage;
    private UI ui;

    /**
     * Constructor for the Parser, which takes in a Storage object to facilitate the updating of the storage
     * file each time there is a task executed
     *
     * @param storage The storage object that handles storing of the tasks to a file so that it could be retrieved
     *                for subsequent uses of the bot
     */
    public Parser(Storage storage, UI ui) {
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * This method interprets the user input and calls the relevant methods to facilitate the request of the user
     *
     * @param userInput the String entered by the user specifying their request
     * @param list      the TaskList object that stores all the user's tasks
     * @return A String that is to be displayed to the user upon completion of their request
     * @throws IOException
     */
    public String parseAndExecute(String userInput, TaskList list) throws IOException {

        String[] userInputComponents = userInput.split(" ");
        if (userInputComponents.length == 0) {
            return ui.printEmptyRequestMessage();
        }

        String requestType = userInputComponents[0];
        try {
            Request request = Request.getRequest(requestType);

            switch (request) {
            case LIST: {
               ListCommand listCommand = new ListCommand(userInput, list, storage);
               return listCommand.execute();
            }
            case MARK: {
                if (userInputComponents.length != 2) {
                    return ui.printRequireExactlyOneArgumentMessage();
                }
                MarkCommand markCommand = new MarkCommand(userInput, list, storage, userInputComponents);
                return markCommand.execute();
            }
            case UNMARK: {
                if (userInputComponents.length != 2) {
                    return ui.printRequireExactlyOneArgumentMessage();
                }
                UnmarkCommand unmarkCommand = new UnmarkCommand(userInput, list, storage, userInputComponents);
                return unmarkCommand.execute();
            }
            case TODO: {
                TodoCommand todoCommand = new TodoCommand(userInput, list, storage, userInputComponents);
                return todoCommand.execute();
            }
            case DEADLINE: {
                String[] splitDeadline = userInput.split("/");
                if (splitDeadline.length != 2) {
                    return ui.printMissingDeadlineMessage();
                }
                DeadlineCommand deadlineCommand = new DeadlineCommand(userInput, list, storage, splitDeadline);
                return deadlineCommand.execute();

            }
            case EVENT: {
                String[] splitTimes = userInput.split("/");
                if (splitTimes.length != 3) {
                    return ui.printRequirementForStartFinishDateTimeMessage();
                }
               DeadlineCommand deadlineCommand = new DeadlineCommand(userInput, list, storage, splitTimes);
                return deadlineCommand.execute();

            }

            case DELETE: {
                if (userInputComponents.length != 2) {
                    return ui.printMissingTaskNumberMessage();
                }
               DeleteCommand deleteCommand = new DeleteCommand(userInput, list, storage, userInputComponents);
                return deleteCommand.execute();
            }

            case FIND: {
                if (userInputComponents.length != 2) {
                    return ui.printOneKeywordMessage();
                }
                FindCommand findCommand = new FindCommand(userInput, list, storage, userInputComponents);
                return findCommand.execute();

            }

            case HELP: {
               return UI.printAvailableCommands();
            }

            case BYE: {
                return ui.printByeMessage();
            }
            default: {
                return ui.printInvalidCommandMessage();
            }

            }
        } catch (DukeException e) {
            return ui.printInvalidDukeRequestMessage();

        }
    }
}


