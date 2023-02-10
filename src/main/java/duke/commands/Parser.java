package duke.commands;

import duke.commands.tasks.Deadline;
import duke.commands.tasks.Event;
import duke.commands.tasks.ToDo;
import duke.dukeexception.DukeException;

/**
 * This class interprets the user command
 */
public class Parser {
    private final String command;

    public Parser(String command) {
        this.command = command.trim();
    }

    private void checkLength(String str) throws DukeException {
        if (str.length() == 0) {
            throw new DukeException("Your item has an empty name!! :(");
        }
    }

    private void checkDate(String str) throws DukeException {
        if (str.length() == 0) {
            throw new DukeException("Empty date(s)!!");
        }
    }

    private String getTaskName() {
        String[] commandArr = this.command.split(" ");
        String taskName = "";
        for (int i = 1; i < commandArr.length
                && !commandArr[i].equals("/by")
                && !commandArr[i].equals("/from"); i++) {
            taskName += commandArr[i] + " ";
        }
        return taskName;
    }

    private String getByTime() {
        String[] commandArr = this.command.split(" ");
        int curInd = 1;
        while (curInd < commandArr.length && !commandArr[curInd].equals("/by")) {
            curInd += 1;
        }
        curInd += 1; // skips "/by"
        String taskDeadline = "";
        while (curInd < commandArr.length) {
            taskDeadline += commandArr[curInd] + " ";
            curInd += 1;
        }
        taskDeadline = taskDeadline.trim();
        return taskDeadline;
    }

    private String[] getEventTimes() {
        String[] commandArr = this.command.split(" ");
        int curInd = 1;
        while (curInd < commandArr.length && !commandArr[curInd].equals("/from")) {
            curInd += 1;
        }
        curInd += 1;
        String from = "";
        while (curInd < commandArr.length && !commandArr[curInd].equals("/to")) {
            from += commandArr[curInd] + " ";
            curInd += 1;
        }
        from = from.trim();
        curInd += 1;
        String to = "";
        while (curInd < commandArr.length) {
            to += commandArr[curInd] + " ";
            curInd += 1;
        }
        to = to.trim();
        return new String[]{from, to};
    }

    /**
     * Interprets the command stored internally within the class
     *
     * @return The command to be executed in a Command class
     * @throws DukeException when command is invalid
     */
    public Command process() throws DukeException {
        String[] commandArr = this.command.split(" ");
        int editIndex = Character.getNumericValue(command.charAt(command.length() - 1)) - 1;
        String description = this.getTaskName();
        switch (commandArr[0]) {
        case "find":
            return new Find(this.command, commandArr[1]);
        case "list":
            return new ListTasks(this.command);
        case "mark":
            return new Mark(this.command, editIndex);
        case "unmark":
            return new Unmark(this.command, editIndex);
        case "todo":
            try {
                checkLength(description);
            } catch (DukeException ex) {
                throw ex;
            }
            return new Add(this.command, new ToDo(description));
        case "deadline":
            // can optimise
            try {
                checkLength(description);
            } catch (DukeException ex) {
                throw ex;
            }
            String byTime = getByTime();
            try {
                checkDate(byTime);
            } catch (DukeException ex) {
                throw ex;
            }
            return new Add(this.command, new Deadline(description, byTime));
        case "event":
            String[] eventTimes = getEventTimes();
            return new Add(this.command, new Event(description, eventTimes[0], eventTimes[1]));
        case "delete":
            return new Delete(this.command, editIndex);
        case "bye":
            return new Exit(this.command);
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
