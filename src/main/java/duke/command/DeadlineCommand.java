package duke.command;

import duke.task.Deadline;
import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeadlineCommand extends Command {
    private String message;
    private String timing;

    public DeadlineCommand(String fullCommand) {
        String[] checker = fullCommand.split("/by ");
        String[] checkerDeadline = checker[0].split("deadline ");
        this.message = checkerDeadline[1];
        this.timing = checker[1];
    }

    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        Deadline taskDeadline = new Deadline(message, timing);
        if (!taskDeadline.checkFormat()) {
            tasks.addToList(taskDeadline);
            System.out.println("Got it, I've added this task:");
            System.out.println(taskDeadline);
        } else {
            System.out.println("Wrong Format, Please fill in with the following format: YYYY-MM-DD h:mm");
        }
        tasks.statement();
        return true;
    }
}
