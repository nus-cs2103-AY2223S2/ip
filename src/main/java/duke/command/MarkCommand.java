package duke.command;

import java.util.ArrayList;

import duke.Duke;
import duke.DukeException;
import duke.task.Task;

/**
 * Handles a request to mark one or more tasks.
 */
public class MarkCommand extends Command {

    /** Keyword of this command */
    private static final String CMD_KEYWORD = "mark";

    /** Separator used between task index */
    private static final String SEPARATOR = " ";

    private final String[] markList;

    /**
     * Constructs an instance of MarkCommand.
     * Extracts which task(s) to mark using the specified user input.
     *
     * @param userInput String containing the whole input provided by the user.
     * @throws DukeException If user did not indicate which task(s) to mark.
     */
    public MarkCommand(String userInput) throws DukeException {
        String taskInfo = userInput.replaceFirst(CMD_KEYWORD, "").trim();

        markList = taskInfo.split(SEPARATOR);
        if (markList[0].isBlank()) {
            throw new DukeException("Please indicate which task(s) to mark.");
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
        ArrayList<Integer> tasksToMark = new ArrayList<>();

        // Parse String->Integer + Bounds checking
        for (String taskNumStr : markList) {
            try {
                int taskIdx = Integer.parseInt(taskNumStr) - 1;
                if (!duke.taskList.isValidIndex(taskIdx)) {
                    throw new DukeException("Index out of bounds.");
                }
                tasksToMark.add(taskIdx);
            } catch (NumberFormatException e) {
                sb.append("\t'" + taskNumStr + "' is not a number.\n");
            } catch (DukeException e) {
                sb.append("\tTask " + taskNumStr + " does not exist.\n");
            }
        }

        if (tasksToMark.isEmpty()) {
            throw new DukeException("None of the input(s) are invalid:\n" + sb);
        }

        duke.ui.println("Nice I've marked the task(s) as done:");
        for (int i = 0; i < duke.taskList.size(); i++) {
            if (tasksToMark.contains(i)) {
                Task task = duke.taskList.get(i);
                task.setDone(true);
                duke.ui.println("\t" + (i + 1) + ". " + task);
            }
        }

        duke.storage.saveDataToFile();

        // Throw exception if any errors were caught earlier
        if (sb.length() > 0) {
            throw new DukeException("However, you have some invalid inputs:\n" + sb);
        }

        return ReturnCode.SUCCESS;
    }
}
