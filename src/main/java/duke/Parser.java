package duke;

import java.util.Arrays;
import duke.task.Task;

/*
 * Deals with making sense of the user command
 */
public class Parser {
    private MainWindow mainWindow;

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
        default:
            throw new InvalidCommandException("Invalid command. Please try again");
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
