package duke.parser;

import duke.exception.WrongFormatException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Processes and handle the list command
 */
public class HandleList {
    public HandleList() {
    }

    /**
     * Check whether the input line is in correct format
     * Calls function in TaskList class to generate the whole list of tasks
     * @param input Entered by user
     * @param tasklist List of existing tasks
     * @param ui Ui that would generate reply for the user
     * @return A String to respond to user through ui, printing the whole list of task
     * @throws WrongFormatException This exception is thrown when input contains more words than "list"
     */
    public static String performList(String input, TaskList tasklist, Ui ui) throws WrongFormatException {
        boolean correctFormat = input.equals("list");

        if (!correctFormat) {
            throw new WrongFormatException("list");
        }

        return ui.showList(tasklist.printList());

    }


}
