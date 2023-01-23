package UserCommands;

import java.util.Scanner;

import Features.DukeException;
import Features.TaskList;
import Features.Ui;

/**
 * Finds and prints tasks that match user input.
 */
public class CommandFind extends Command {

    /**
     * Prints tasks that match user input.
     * @param userScan Scanner object containing user input.
     * @param taskList List of existing tasks.
     * @throws DukeException  If the find format is wrong.
     */
    public void print(Scanner userScan, TaskList taskList) throws DukeException {
        // ERROR: find format is anything other than [ find <insert query> ]
        Ui ui = new Ui();
        boolean isEmpty = true;
        String query = userScan.nextLine().strip();
        if (query.length() == 0) {
            throw new DukeException(ui.formatLogicError("search query cannot be empty."));
        } else {
            StringBuilder toPrint = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getName().toLowerCase().contains(query.toLowerCase())) {
                    if (i < taskList.size() && !isEmpty) {
                        toPrint.append("\n");
                    }
                    toPrint.append(i + 1).append(". ").append(taskList.get(i).toString());
                    isEmpty = false;
                }
            }
            if (!isEmpty) {
                ui.print("Here are the tasks that matched your query:\n" + toPrint);
            } else {
                ui.print("Sorry, nothing matches your search query!");
            }
        }
    }

    @Override
    public TaskList handle(Scanner userScan, TaskList taskList) throws DukeException {
        return new TaskList();
    }
}
