package duke;

import java.util.Arrays;
import duke.task.Task;

/*
 * Deals with making sense of the user command
 */
public class Parser {

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
     */
    public boolean runCommand(TaskList tasks, Storage storage, String[] words) throws IndexOutOfBoundsException,
            NumberFormatException, InvalidCommandException {

        boolean hasTaskChanged = false;
        words[0] = words[0].toUpperCase();
        switch(words[0]) {
        case "BYE":
            System.out.println("Bye. Hope to see you again soon!");
            return true;
        case "LIST":
            tasks.printList();
            break;
        case "MARK":
            Task markTask = tasks.getTask(Integer.parseInt(words[1]));
            markTask.mark();
            hasTaskChanged = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(markTask);
            break;
        case "UNMARK":
            Task unmarkTask = tasks.getTask(Integer.parseInt(words[1]));
            unmarkTask.unmark();
            hasTaskChanged = true;
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(unmarkTask);
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
        default:
            throw new InvalidCommandException("Invalid command. Please try again");
        }
        if(words[0] != "LIST" && hasTaskChanged) {
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
