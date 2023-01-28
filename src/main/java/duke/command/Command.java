package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Abstract class to allow different commands to be inherited from.
 */
public abstract class Command {

    /**
     * Abstract method to be used by the other commands from inherited class.
     *
     * @param tasks   - list of tasks.
     * @param ui      - interface.
     * @param storage - harddisk store using textfile.
     * @return boolean - true or false depending on the results.
     */
    public abstract boolean execute(Storage tasks, Ui ui, Storage storage);

    /**
     * Check if program needs to be exited from command bye.
     *
     * @return boolean - true or false depending upon the command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Check if the format of the commands are correct.
     *
     * @param cmd - The given command description entered by the user.
     *
     * @return boolean - true or false according to the formatting.
     * @throws DukeException - Error of the format of the command is incorrect.
     */
    public boolean checkCommand(String cmd) throws DukeException {
        String cmdtype = cmd.split(" ")[0];
        // check that duke.command list must not have any more description
        if (cmdtype.equals("list") && cmdtype.length() != cmd.length()) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but list cannot have a description.");
        } else if (cmdtype.equals("todo") && cmdtype.length() == cmd.length()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (cmdtype.equals("deadline")) {
            try {
                int indexOfBy = cmd.indexOf("/by ");
                int indexOfDate = indexOfBy + 4;
                String activity = cmd.substring(cmdtype.length() + 1, indexOfBy - 1);
                String date = cmd.substring(indexOfDate);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The format of a deadline: deadline {activity} /by {date}.");
            }
        } else if (cmdtype.equals("event")) {
            try {
                int indexOfFrom = cmd.indexOf("/from ");
                int indexOfFromTime = indexOfFrom + 6;
                int indexOfTo = cmd.indexOf("/to ");
                int indexOfToTime = indexOfTo + 4;
                String activity = cmd.substring(cmdtype.length() + 1, indexOfFrom - 1);
                String from = cmd.substring(indexOfFromTime, indexOfTo - 1);
                String to = cmd.substring(indexOfToTime);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The format of a event: event {activity} /from {datetime} /to {datetime}.");
            }
        }
        return true;
    }
}