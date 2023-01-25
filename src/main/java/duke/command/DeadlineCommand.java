package duke.command;

import duke.task.Deadline;
import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

<<<<<<< .merge_file_1a10U7
/**
 * Deadline command for the tasks with deadline.
 */
=======
>>>>>>> .merge_file_7I81ap
public class DeadlineCommand extends Command {
    private String message;
    private String timing;

    public DeadlineCommand(String fullCommand) {
        String[] checker = fullCommand.split("/by ");
<<<<<<< .merge_file_1a10U7
        System.out.println(checker[1]);
        String[] checkerdeadline = checker[0].split("deadline ");
        this.message = checkerdeadline[1];
=======
        String[] checkerDeadline = checker[0].split("deadline ");
        this.message = checkerDeadline[1];
>>>>>>> .merge_file_7I81ap
        this.timing = checker[1];
    }

    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
<<<<<<< .merge_file_1a10U7
        Deadline t = new Deadline(message, timing);
        if (!t.checkFormat()) {
            tasks.addToList(t);
            System.out.println("Got it, I've added this task:");
            System.out.println(t);
=======
        Deadline taskDeadline = new Deadline(message, timing);
        if (!taskDeadline.checkFormat()) {
            tasks.addToList(taskDeadline);
            System.out.println("Got it, I've added this task:");
            System.out.println(taskDeadline);
>>>>>>> .merge_file_7I81ap
        } else {
            System.out.println("Wrong Format, Please fill in with the following format: YYYY-MM-DD h:mm");
        }
        tasks.statement();
        return true;
    }
}
