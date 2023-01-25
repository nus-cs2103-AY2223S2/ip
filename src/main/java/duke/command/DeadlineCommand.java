package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;

public class DeadlineCommand extends Command {
    
    public DeadlineCommand(String[] command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            StringBuilder sb = new StringBuilder();
            StringBuilder by = new StringBuilder();
            boolean b = false;
            for (int i = 1; i < command.length; i++) {
                if (b) {
                    by.append(command[i]);
                    if (i + 1 != command.length) {
                        by.append(" ");
                    }
                } else {
                    if (command[i].equals("/by")) {
                        b = true;
                        sb.setLength(sb.length() - 1);
                        continue;
                    }
                    sb.append(command[i]);
                    sb.append(" ");
                }
            }
            if (by.length() == 0) {
                throw new DukeException(null, null);
            }
            tasks.add(new Deadline(sb.toString(), by.toString()));
            ui.addMsg(tasks);
            storage.write(tasks);
        } catch (DukeException e) {
            ui.deadlineError();
        }
    }
}