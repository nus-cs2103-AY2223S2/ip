package duke.commands;

import duke.Storage;
import duke.Ui;

import duke.tasks.Deadline;
import duke.tasks.TaskList;

import java.time.LocalDate;

public class DeadlineCommand implements Command {
    private final String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String[] s1 = input.substring(9).split("/by");
        Deadline newDeadline = new Deadline(s1[0].trim(), LocalDate.parse(s1[1].trim(),
                Deadline.getDeadlineFormatter()));
        taskList.addTask(newDeadline);
        ui.showAddedMessage(newDeadline, taskList);
    }
}