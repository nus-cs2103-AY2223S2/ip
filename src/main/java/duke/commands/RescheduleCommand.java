package duke.commands;

import duke.Ui;
import duke.exceptions.IncorrectIndexException;
import duke.exceptions.NeroException;
import duke.exceptions.RescheduleException;
import duke.task.*;

public class RescheduleCommand extends Command {

    public RescheduleCommand(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    @Override
    public String executeCommand(String userInput) throws NeroException {
        String[] input = userInput.split(" ");
        try {
            int taskIndex = Integer.parseInt(input[1]) - 1;
            if (taskIndex >= taskList.getSize() || taskIndex < 0) {
                throw new IncorrectIndexException(taskList.getSize());
            }
            Task currTask = taskList.get(taskIndex);
            if (currTask instanceof ToDo) {
                return ui.printFailedReschedule();
            } else if (currTask instanceof Deadline) {
                String newDeadline = input[3];
                Task newTask = new Deadline(currTask.getDescription(), newDeadline);
                taskList.set(taskIndex, newTask);
                return ui.printSuccessfulReschedule(newDeadline.toString());
            } else {
                String startDate = input[3];
                String endDate = input[5];
                Task newTask = new Event(currTask.getDescription(), startDate, endDate);
                taskList.set(taskIndex, newTask);
                return ui.printSuccessfulReschedule(newTask.toString());
            }
        } catch (IndexOutOfBoundsException e) {
            throw new RescheduleException();
        } catch (NumberFormatException e) {
            throw new RescheduleException();
        }
    }
}
