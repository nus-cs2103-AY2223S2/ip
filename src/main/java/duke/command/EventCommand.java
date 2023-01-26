package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Event;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * A subclass of Command that represents the command
 * to add an event task into the TaskList.
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public class EventCommand extends Command {

    /**
     * Constructor of EventCommand.
     * @param command Command from the user.
     */
    public EventCommand(String[] command) {
        super(command);
    }

    /**
     * Method to add an event task to the TaskList.
     * @param tasks List of tasks.
     * @param ui Ui of the chat.
     * @param storage Storage of Duke.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder startTime = new StringBuilder();
            StringBuilder endTime = new StringBuilder();
            boolean fr = false;
            boolean t = false;
            for (int i = 1; i < command.length; i++) {
                if (fr) {
                    if (command[i].equals("/to")) {
                        t = true;
                        fr = false;
                        startTime.setLength(startTime.length()- 1);
                        continue;
                    }
                    startTime.append(command[i]);
                    startTime.append(" ");
                } else if (t) {
                    endTime.append(command[i]);
                    if (i + 1 != command.length) {
                        endTime.append(" ");
                    }
                } else {
                    if (command[i].equals("/from")) {
                        fr = true;
                        stringBuilder.setLength(stringBuilder.length() - 1);
                        continue;
                    }
                    stringBuilder.append(command[i]);
                    stringBuilder.append(" ");
                }
            }
            if (startTime.length() == 0 || endTime.length() == 0) {
                throw new DukeException(null, null);
            }
            tasks.add(new Event(stringBuilder.toString(), startTime.toString(), endTime.toString()));
            ui.addMsg(tasks);
            storage.saveToDisk(tasks);
        } catch (DukeException e) {
            ui.eventError();
        }
    }
    
}