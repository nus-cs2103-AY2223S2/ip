package duke.commands;

import duke.Ui;
import duke.exceptions.IncorrectCapitalisationException;
import duke.exceptions.IncorrectDeadlineFormatException;
import duke.exceptions.NeroException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command created when user types deadline as first word in input
 */
public class DeadlineCommand extends Command {

    public DeadlineCommand(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executeCommand(String userInput) throws NeroException {
        try {
            //cleanedString contains [description, duration]
            String[] cleanedString = cleanDeadline(userInput);
            Task newTask = new Deadline(cleanedString[0], cleanedString[1]);
            taskList.addTask(newTask);
            return ui.printAddedTasks(newTask.toString(), taskList.getSize());
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectDeadlineFormatException();
        }
    }

    /**
     * performs cleaning on deadline input
     * @param inputString input entered by user
     * @return String array containing [description, deadline]
     * @throws NeroException incorrect capitalisation exception and incorrect date format exception
     */
    String[] cleanDeadline(String inputString) throws NeroException {
        String[] cleanedString = new String[2];
        String[] splitString = inputString.split("/");
        if (splitString[0].indexOf("deadline") == -1) {
            throw new IncorrectCapitalisationException();
        }
        String description = splitString[0].replace("deadline", "");
        String deadline = splitString[1].replace("by", "");
        cleanedString[0] = description;
        cleanedString[1] = deadline;
        return cleanedString;
    }
}
