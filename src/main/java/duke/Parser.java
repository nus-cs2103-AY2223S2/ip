package duke;

import java.util.Arrays;
import duke.task.Task;

/*
 * Deals with making sense of the user command
 */
public class Parser {
    private MainWindow mainWindow;
    private static final String BULLET_POINT = "\u2022";
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

        boolean hasTaskChanged = false;
        words[0] = words[0].toUpperCase();
        switch(words[0]) {
        case "BYE":
            mainWindow.sendDukeResponse("Bye. Hope to see you again soon!");
            return true;
        case "LIST":
            tasks.printList();
            break;
        case "MARK":
            Task markTask = tasks.getTask(Integer.parseInt(words[1]));
            markTask.mark();
            hasTaskChanged = true;
            mainWindow.sendDukeResponse("Nice! I've marked this task as done:");
            mainWindow.sendDukeResponse(markTask.toString());
            break;
        case "UNMARK":
            Task unmarkTask = tasks.getTask(Integer.parseInt(words[1]));
            unmarkTask.unmark();
            hasTaskChanged = true;
            mainWindow.sendDukeResponse("OK, I've marked this task as not done yet:");
            mainWindow.sendDukeResponse(unmarkTask.toString());
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
            if (words.length <= 1) {
                throw new EmptyDescriptionException("Search keyword cannot be empty");
            }
            String[] findWords = Arrays.copyOfRange(words, 1, words.length);
            tasks.findTask(String.join(" ", findWords));
            break;
        case "HELP":
            String commands = "List of commands:\n"
                    + BULLET_POINT
                    + "Todo [Description] \n"
                    + "    - Adds a todo task with description\n"
                    + BULLET_POINT
                    + "Deadline [Description] \\by [Date and time in this format DD/MM/YYYY HHmm]\n"
                    + "    - Adds a deadline task with description and deadline date\n"
                    + BULLET_POINT
                    + "Event [Description] \\from [Date and time in this format DD/MM/YYYY HHmm] "
                    + "\\to [Date and time]\n" +
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
            mainWindow.sendDukeResponse(commands);
            break;
        default:
            throw new InvalidCommandException("Invalid command. Please try again." + "\nType \"help\" to see all commands");
        }
        if (hasTaskChanged) {
            storage.saveFile(tasks.getListOfTask());
        }
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
}
