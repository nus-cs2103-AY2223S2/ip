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
        System.out.println(checker[1]);
        String[] checkerdeadline = checker[0].split("deadline ");
        this.message = checkerdeadline[1];
        this.timing = checker[1];
    }

    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        Deadline t = new Deadline(message, timing);
        if (!t.checkFormat()) {
            tasks.addToList(t);
            System.out.println("Got it, I've added this task:");
            System.out.println(t);
        } else {
            System.out.println("Wrong Format, Please fill in with the following format: YYYY-MM-DD h:mm");
        }
        tasks.statement();
        return true;
    }
}
