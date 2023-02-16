package duke.commands;

import duke.Ui;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.IncorrectCapitalisationException;
import duke.exceptions.IncorrectEventFormatException;
import duke.exceptions.NeroException;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command created when user types event as first word in input
 */
public class EventCommand extends Command {

    public EventCommand(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executeCommand(String userInput) throws NeroException {
        try {
            //cleanedString contains [description, startDate, endDate]
            String[] cleanedString = cleanEvent(userInput);
            Task newTask = new Event(cleanedString[0], cleanedString[1], cleanedString[2]);
            taskList.addTask(newTask);
            return ui.printAddedTasks(newTask.toString(), taskList.getSize());
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectEventFormatException();
        }
    }

    /**
     * performs string cleaning on event input
     * @param inputString input entered by user
     * @return String array containing [description, start date, end date]
     * @throws NeroException incorrect capitalisation exception and incorrect date exception
     */
    String[] cleanEvent(String inputString) throws NeroException {
        String[] cleanedString = new String[3];
        String[] splitString = inputString.split("/");
        if (splitString[0].indexOf("event") == -1) {
            throw new IncorrectCapitalisationException();
        }
        String description = splitString[0].replace("event", "");
        if (description.length() == 0) {
            throw new EmptyDescriptionException();
        }
        String startDate = splitString[1].replace("from", "");
        String endDate = splitString[2].replace("to", "");
        cleanedString[0] = description;
        cleanedString[1] = startDate;
        cleanedString[2] = endDate;
        return cleanedString;
    }
}
