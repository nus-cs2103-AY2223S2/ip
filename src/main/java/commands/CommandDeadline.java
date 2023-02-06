package commands;

import features.DukeException;
import features.Storage;
import features.TaskList;
import features.Ui;
import tasks.Deadline;
import tasks.Task;

/**
 * Handles 'deadline' command.
 */
public class CommandDeadline extends Command {
    /**
     * Adds a Deadline task to the taskList and returns a String form of the action.
     * @param userInput The user's String input in array form.
     * @return The deadline addition confirmation message.
     * @throws DukeException Thrown if an error occurs.
     */
    @Override
    public String handle(String[] userInput) throws DukeException {
        Ui ui = new Ui();
        TaskList taskList = new Storage().loadTaskList();
        try {
            String deadlineSentence = userInput[1];
            String deadlineName = getDeadlineName(deadlineSentence);
            String deadlineDate = getDeadlineDate(deadlineSentence);
            assert (!deadlineName.equals("") && !deadlineDate.equals(""));
            Task deadlineToAdd = new Deadline(deadlineName, deadlineDate);
            taskList.add(deadlineToAdd);
            autoSave(taskList);
            return ("Task added:\n " + deadlineToAdd + "\n" + "There are now " + taskList.size()
                    + " task(s) in your list.");
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException err) {
            throw new DukeException(ui.formatCommandError("deadline",
                    "deadline <insert description> " + "/by <insert deadline>"));
        }
    }
    public String getDeadlineName(String deadlineSentence) throws StringIndexOutOfBoundsException,
            ArrayIndexOutOfBoundsException, DukeException {
        Ui ui = new Ui();
        String deadlineName = deadlineSentence.substring(0, deadlineSentence.indexOf(" /by"));
        // ERROR: deadline description is blank.
        if (deadlineName.strip().length() == 0) {
            throw new DukeException(ui.formatLogicError("deadline description cannot be blank."));
        }
        return deadlineName;
    }
    public String getDeadlineDate(String deadlineSentence) throws StringIndexOutOfBoundsException,
            ArrayIndexOutOfBoundsException, DukeException {
        Ui ui = new Ui();
        String deadlineDate = deadlineSentence.substring(deadlineSentence.indexOf(" /by") + 5);
        // ERROR: deadline date is blank.
        if (deadlineDate.strip().length() == 0) {
            throw new DukeException(ui.formatLogicError("deadline date cannot be blank."));
        }
        return deadlineDate;
    }
}
