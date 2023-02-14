package duke.parser;

import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.exceptions.DukeException;
import duke.ui.Sender;

/**
 * Parses user input and returns a response.
 */
public class Parser {

    /** Handles the output of responses to the user. */
    private Sender sender;

    /**
     * Constructs a new Parser.
     * @param storage the Storage class
     * @param tasks the TaskList class
     */
    public Parser(Storage storage, TaskList tasks) {
        this.sender = new Sender(storage, tasks);
    }

    /**
     * Parses user input and produces an output.
     * @param userInput the user input
     * @exception DukeException error thrown when user gives an invalid input
     */
    public String parse(String userInput) throws DukeException {
        String[] inputSplitByWords = userInput.split(" ", 2);
        String firstWord = inputSplitByWords[0];
        try {
            switch (firstWord) {
                case "bye":
                    return sender.sendGoodByeMessage();
                case "list":
                    return sender.listAllTasks();
                case "help":
                    return sender.sendHelpMessage();
                case "find":
                    //Body message should be a keyword to search for a task
                    String searchWord = inputSplitByWords[1];
                    return sender.listMatchingTasks(searchWord);
                case "mark":
                    //second item in the String array should be an integer indicating which Task to mark or unmark.
                    int taskNumberToMark = Integer.parseInt(inputSplitByWords[1]);
                    return sender.markTask(taskNumberToMark);
                case "unmark":
                    //second item in the String array should be an integer indicating which Task to mark or unmark.
                    int taskNumberToUnmark = Integer.parseInt(inputSplitByWords[1]);
                    return sender.unmarkTask(taskNumberToUnmark);
                case "todo":
                    //Rest of message describes the Task.
                    String bodyToDo = userInput.split(" ", 2)[1];
                    return sender.addToDo(bodyToDo);
                case "deadline":
                    //Rest of message describes the Task.
                    String bodyDeadline = userInput.split(" ", 2)[1];
                    return sender.addDeadLine(bodyDeadline);
                case "event":
                    //Rest of message describes the Task.
                    String bodyEvent = userInput.split(" ", 2)[1];
                    return sender.addEvent(bodyEvent);
                case "doafter":
                    //Rest of message describes the Task.
                    String bodyDoAfter = userInput.split(" ", 2)[1];
                    return sender.addDoAfter(bodyDoAfter);
                case "delete":
                    //second word should be an integer
                    int taskNumberToDelete = Integer.parseInt(inputSplitByWords[1]);
                    return sender.deleteTask(taskNumberToDelete);
                default:
                    return sender.sendInvalidCommandMessage();
            }
        } catch (DukeException e) {
            return e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DukeException("There seems to be something wrong with your command." +
                    " For a list of valid commands to use, " +
                    "type in 'help'.");
        }
    }
}

