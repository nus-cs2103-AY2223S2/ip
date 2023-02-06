package commands;

import features.DukeException;
import features.Storage;
import features.TaskList;
import features.Ui;

/**
 * Finds and returns tasks that match user input in String form.
 */
public class CommandFind extends Command {
    /**
     * Finds and returns tasks in String form that match the user input query.
     * @param userInput The user's String input in array form.
     * @return The find confirmation message.
     * @throws DukeException Thrown if an error occurs.
     */
    @Override
    public String handle(String[] userInput) throws DukeException {
        // ERROR: find format is anything other than [ find <insert query> ]
        Ui ui = new Ui();
        TaskList taskList = new Storage().loadTaskList();
        boolean isEmpty = true;
        try {
            String query = userInput[1].strip();
            if (userInput.length != 2) {
                throw new DukeException(ui.formatLogicError("search query cannot be empty."));
            }
            StringBuilder toPrint = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                if (matchesName(taskList, i, query)) {
                    if (i < taskList.size() && !isEmpty) { //if end of TaskList has been reached.
                        toPrint.append("\n");
                    }
                    toPrint.append(i + 1).append(". ").append(taskList.get(i).toString());
                    isEmpty = false;
                }
            }
            if (!isEmpty) {
                assert (!toPrint.toString().equals(""));
                return ("Here are the tasks that matched your query:\n" + toPrint);
            } else {
                return ("Sorry, nothing matches your search query!");
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeException(ui.formatLogicError("search query cannot be empty."));
        }
    }
    /**
     * Returns true if subject in taskList at index i is the same as the query.
     * @param taskList The user's TaskList.
     * @param index The index in the taskList where the subject is found.
     * @param query The query to compare the subject with.
     * @return True if the subject matches the query, false otherwise.
     */
    public boolean matchesName(TaskList taskList, int index, String query) {
        String subject = taskList.get(index).getName();
        return subject.toLowerCase().contains(query.toLowerCase());
    }
}
