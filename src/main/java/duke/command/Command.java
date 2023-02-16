package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;


/**
 * Abstract class to allow different commands to be inherited from.
 */
public abstract class Command {

    /**
     * Abstract method to be used by the other commands from inherited class.
     *
     * @param tl      - list of tasks.
     * @param storage - harddisk store using textfile.
     * @return String  - returns the result of the command.
     */
    public abstract String execute(TaskList tl, Storage storage);

    /**
     * Checks that the format of the commands are correct.
     *
     * @param cmd - The given command description entered by the user.
     * @return boolean - true or false according to the formatting.
     * @throws DukeException - Error of the format of the command is incorrect.
     */
    public static boolean isValidCommand(String cmd) throws DukeException {
        String cmdtype = cmd.split(" ")[0];
        // check that duke.command list must not have any more description
        if (cmdtype.equals("list") && cmdtype.length() != cmd.length()) {
            throw new DukeException("I'm sorry, but list cannot have a description.");
        } else if (cmdtype.equals("todo") && cmdtype.length() == cmd.length()) {
            throw new DukeException("The description of a todo cannot be empty.");
        } else if (cmdtype.equals("deadline")) {
            try {
                int indexOfBy = cmd.indexOf("/by ");
                int indexOfDate = indexOfBy + 4;
                String activity = cmd.substring(cmdtype.length() + 1, indexOfBy - 1);
                String datetime = cmd.substring(indexOfDate);
                LocalDate date = LocalDate.parse(datetime.split(" ")[0]);
                LocalTime time = LocalTime.parse(datetime.split(" ")[1], DateTimeFormatter.ofPattern("HHmm"));
                if (date.isBefore(LocalDate.now())) {
                    throw new DukeException("The given date has passed! Please enter a new date.");
                } else if (time.isBefore(LocalTime.now())) {
                    throw new DukeException("The given time has passed! Please enter a new time.");
                }
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("The format of a deadline: "
                        + "deadline {task} /by {YYYY-MM-DD} {HHMM}");
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
                throw new DukeException("The format of a event: "
                        + "event {task} /from {datetime} /to {datetime}");
            }
        }
        return true;
    }
}
