package duke;

import java.util.Arrays;
import duke.task.Task;

/*
 * Deals with making sense of the user command
 */
public class Parser {
    private MainWindow mainWindow;
    private static final String BULLET_POINT = "\u2022";
    private boolean hasTaskChanged = false;
    private String displayMessage;
    /**
     * Constructs a new Parser instance
     *
     * @param mainWindow Controller for MainWindow
     */
    public Parser(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Runs the input command given by the user and write into file if list has changed
     * 
     * @param tasks Arraylist containing task objects
     * @param storage Storage class that manages save and loading
     * @param words User input
     * @return true if command is bye, otherwise return false
     * @throws IndexOutOfBoundsException When index given is larger than the taskList
     * @throws NumberFormatException When a string is given instead of a number for some commmands
     * @throws InvalidCommandException When user inputs an invalid command
     * @throws EmptyDescriptionException When user inputs an empty description
     */
    public boolean runCommand(TaskList tasks, Storage storage, String[] words) throws IndexOutOfBoundsException,
            NumberFormatException, InvalidCommandException, EmptyDescriptionException {
        hasTaskChanged = false;
        displayMessage = "";
        words[0] = words[0].toUpperCase();
        switch(words[0]) {
        case "BYE":
            mainWindow.sendDukeResponse("Bye. Hope to see you again soon!");
            return true;
        case "LIST":
            tasks.printList();
            break;
        case "MARK":
            markTask(words[1],tasks);
            break;
        case "UNMARK":
            unmarkTask(words[1],tasks);
            break;
        case "TODO":
        case "DEADLINE":
        case "EVENT":
            String[] sliceWords = Arrays.copyOfRange(words, 1, words.length);
            hasTaskChanged = tasks.addTask(sliceWords, words[0]);
            break;
        case "DELETE":
            hasTaskChanged = tasks.deleteTask(Integer.parseInt(words[1]));
            break;
        case "FIND":
            findTask(words,tasks);
            break;
        case "HELP":
            mainWindow.sendDukeResponse(getHelpMessage());
            break;
        default:
            throw new InvalidCommandException("Invalid command. Please try again." + "\nType \"help\" to " +
                    "see all commands");
        }
        commandHelper(storage, tasks);
        return false;
    }


    /**
     * Returns the index of given word
     * 
     * @param words Array of words to look from
     * @param word Word to look for
     * @return The index of the given word, if it doesnt exist, return -1
     */
    public static int getIndexOfWord(String[] words, String word) {
        int index = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Marks a task and change display message
     *
     * @param taskNum Task index number
     * @param tasks List of tasks
     */
    public void markTask(String taskNum, TaskList tasks) {
        Task markTask = tasks.getTask(Integer.parseInt(taskNum  ));
        markTask.mark();
        hasTaskChanged = true;
        displayMessage = "Nice! I've marked this task as done:\n" +  markTask;
    }

    /**
     * Unmarks a task and change display message
     *
     * @param taskNum Task index number
     * @param tasks List of tasks
     */
    public void unmarkTask(String taskNum, TaskList tasks) {
        Task unmarkTask = tasks.getTask(Integer.parseInt(taskNum));
        unmarkTask.unmark();
        hasTaskChanged = true;
        displayMessage = "Nice! I've marked this task as done:\n" +  unmarkTask;
    }

    /**
     * Find task based on keyword
     *
     * @param words  Input words by users
     * @param tasks List of tasks
     */
    public void findTask(String[] words, TaskList tasks) throws EmptyDescriptionException {
        if (words.length <= 1) {
            throw new EmptyDescriptionException("Search keyword cannot be empty");
        }
        String[] findWords = Arrays.copyOfRange(words, 1, words.length);
        tasks.findTask(String.join(" ", findWords));
    }

    /**
     * Saves file and display message on MainWindow
     *
     * @param storage Saved data
     * @param tasks List of tasks
     */
    public void commandHelper(Storage storage, TaskList tasks) {
        if (hasTaskChanged) {
            storage.saveFile(tasks.getListOfTask());
        }
        if(displayMessage != "") {
            mainWindow.sendDukeResponse(displayMessage);
        }
    }


    /**
     * Returns help message
     *
     * @return String containing the help message
     */
    public static String getHelpMessage() {
        String helpCommand = "List of commands:\n"
                + BULLET_POINT
                + "Todo [Description] \n"
                + "    - Adds a todo task with description\n"
                + BULLET_POINT
                + "Deadline [Description] /by [Date and time in this format DD/MM/YYYY HHmm]\n"
                + "    - Adds a deadline task with description and deadline date\n"
                + BULLET_POINT
                + "Event [Description] /from [Date and time in this format DD/MM/YYYY HHmm] "
                + "/to [Date and time]\n" +
                "    - Adds an event task with description, start and end date \n"
                + BULLET_POINT
                + "Delete [Index]\n"
                + "    - Deletes a task at that index\n"
                + BULLET_POINT
                + "Mark [Index]\n"
                + "    - Marks a task at that index as completed\n"
                + BULLET_POINT
                + "Unmark [Index]\n"
                + "    - Unmarks a task at that index as not done\n"
                + BULLET_POINT
                + "Find [Keyword]\n"
                + "    - Lists all task that matches the keyword";
        return  helpCommand;
    }
}


