package duke.command;

import java.util.ArrayList;
import java.util.Collections;

import duke.Duke;
import duke.DukeException;

/**
 * Handles a request to delete one or more tasks.
 */
public class DeleteCommand extends Command {

    /** Keyword of this command */
    private static final String CMD_KEYWORD = "delete";

    /** Separator used between task index */
    private static final String SEPARATOR = " ";

    private final String[] deleteList;

    /**
     * Constructs an instance of DeleteCommand.
     * Using the specified user input, extract which task(s) to delete.
     *
     * @param userInput String containing the whole input provided by the user.
     * @throws DukeException If user did not indicate which task(s) to delete.
     */
    public DeleteCommand(String userInput) throws DukeException {
        String taskInfo = userInput.replaceFirst(CMD_KEYWORD, "").trim();

        deleteList = taskInfo.split(SEPARATOR);
        if (deleteList[0].isBlank()) {
            throw new DukeException("Please indicate which task(s) to delete.");
        }

    }

    /**
     * @inheritDoc
     * @throws DukeException If Index is out of bounds.
     *                       If all provided inputs are invalid.
     *                       If one or more inputs are invalid.
     */
    @Override
    public ReturnCode execute(Duke duke) throws DukeException {
        // TODO: need to DRY this code

        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> tasksToDelete = new ArrayList<>();

        // Parse String->Integer + Bounds checking
        for (String taskNumStr : deleteList) {
            try {
                int taskIdx = Integer.parseInt(taskNumStr) - 1;
                if (!duke.taskList.isValidIndex(taskIdx)) {
                    throw new DukeException("Index out of bounds.");
                }
                tasksToDelete.add(taskIdx);
            } catch (NumberFormatException e) {
                sb.append("\t'" + taskNumStr + "' is not a number.\n");
            } catch (DukeException e) {
                sb.append("\tTask " + taskNumStr + " does not exist.\n");
            }
        }

        if (tasksToDelete.isEmpty()) {
            throw new DukeException("None of the input(s) are invalid:\n" + sb);
        }

        duke.ui.println("I've removed the task(s):");

        // Actual delete from tasklist (start from the back)
        Collections.sort(tasksToDelete);
        Collections.reverse(tasksToDelete);
        for (int i : tasksToDelete) {
            duke.ui.println("\t" + duke.taskList.remove(i));
        }

        duke.storage.saveDataToFile();

        // Throw exception if any errors were caught earlier
        if (sb.length() > 0) {
            throw new DukeException("However, you have some invalid inputs:\n" + sb);
        }

        duke.displayTaskCount();
        return ReturnCode.SUCCESS;
    }
}
