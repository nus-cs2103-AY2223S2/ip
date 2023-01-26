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
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder endTime = new StringBuilder();
            boolean b = false;
            for (int i = 1; i < command.length; i++) {
                if (b) {
                    endTime.append(command[i]);
                    if (i + 1 != command.length) {
                        endTime.append(" ");
                    }
                } else {
                    if (command[i].equals("/by")) {
                        b = true;
                        stringBuilder.setLength(stringBuilder.length() - 1);
                        continue;
                    }
                    stringBuilder.append(command[i]);
                    stringBuilder.append(" ");
                }
            }
            if (endTime.length() == 0) {
                throw new DukeException(null, null);
            }
            tasks.add(new Deadline(stringBuilder.toString(), endTime.toString()));
            ui.addMsg(tasks);
            storage.saveToDisk(tasks);
        } catch (DukeException e) {
            ui.deadlineError();
        }
    }
}