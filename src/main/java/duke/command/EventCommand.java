package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Event;
import duke.exception.DukeException;
import duke.task.TaskList;

public class EventCommand extends Command {

    public EventCommand(String[] command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            StringBuilder sb = new StringBuilder();
            StringBuilder from = new StringBuilder();
            StringBuilder to = new StringBuilder();
            boolean fr = false;
            boolean t = false;
            for (int i = 1; i < command.length; i++) {
                if (fr) {
                    if (command[i].equals("/to")) {
                        t = true;
                        fr = false;
                        from.setLength(from.length()- 1);
                        continue;
                    }
                    from.append(command[i]);
                    from.append(" ");
                } else if (t) {
                    to.append(command[i]);
                    if (i + 1 != command.length) {
                        to.append(" ");
                    }
                } else {
                    if (command[i].equals("/from")) {
                        fr = true;
                        sb.setLength(sb.length() - 1);
                        continue;
                    }
                    sb.append(command[i]);
                    sb.append(" ");
                }
            }
            if (from.length() == 0 || to.length() == 0) {
                throw new DukeException(null, null);
            }
            tasks.add(new Event(sb.toString(), from.toString(), to.toString()));
            ui.addMsg(tasks);
            storage.write(tasks);
        } catch (DukeException e) {
            ui.eventError();
        }
    }
    
}