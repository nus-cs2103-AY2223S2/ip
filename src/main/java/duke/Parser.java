package duke;

import java.util.Arrays;
import duke.task.Task;

public class Parser {

    /**
     * Run the input command given by the user and write into file if list has changed
     * 
     * @param taskList Arraylist containing task objects
     * @param storage Storage class that manages save and loading
     * @param words User input
     * @return return True if command is bye, otherwise return false
     */
    public boolean runCommand(TaskList taskList, Storage storage, String[] words) throws IndexOutOfBoundsException,
            NumberFormatException, InvalidCommandException {

        boolean hasTaskChanged = false;
        words[0] = words[0].toUpperCase();
        switch(words[0]) {
        case "BYE":
            System.out.println("Bye. Hope to see you again soon!");
            return true;
        case "LIST":
            taskList.printList();
            break;
        case "MARK":
            Task markTask = taskList.getTask(Integer.parseInt(words[1]));
            markTask.mark();
            hasTaskChanged = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(markTask);
            break;
        case "UNMARK":
            Task unmarkTask = taskList.getTask(Integer.parseInt(words[1]));
            unmarkTask.unmark();
            hasTaskChanged = true;
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(unmarkTask);
            break;
        case "TODO":
        case "DEADLINE":
        case "EVENT":
            String[] sliceWords = Arrays.copyOfRange(words, 1, words.length);
            hasTaskChanged = taskList.addTask(sliceWords, words[0]);
            break;
        case "DELETE":
            hasTaskChanged = taskList.deleteTask(Integer.parseInt(words[1]));
            break;
        default:
            throw new InvalidCommandException("");
        }
        if(words[0] != "LIST" && hasTaskChanged) {
            storage.saveFile(taskList.getListOfTask());
        }
        return false;
    }


    /**
     * Return index of given word
     * 
     * @param words the array of words to look from
     * @param word the word to look for
     * @return return the index of the given word, if it doesnt exist, return -1
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
