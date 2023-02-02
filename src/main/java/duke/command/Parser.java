package duke.command;

import duke.exception.MissingDescriptionException;
import duke.exception.InvalidCmdException;
import duke.tasklist.Todo;
import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.tasklist.Event;
import duke.tasklist.Deadline;

/**
 * Represents an input Parser.
 * A Parser decodes User input and allows Duke to process these inputs.
 */
public class Parser {
    private TaskList tasks;

    public Parser(TaskList arrList) {
        this.tasks = arrList;
    }

    public boolean isExit(String command) {
        if (command.equals("bye")) {
            return true;
        }
        return false;
    }
        /**
     * Returns a boolean value after parsing User input, to indicate whether
     * the program should keep running.
     *
     * @param command String representation of User input.
     * @return boolean that determines whether Duke continues to run.
     */
    public String parse(String command) {
        Task task;
        int indx;
        String toReturn;
        switch(command) {
        case "bye":
            return "Chenquieh. Hope to see you again Premier Azamat!";
        case "list":
            String taskString = this.tasks.printList();
            toReturn = taskString;
            return toReturn;
        default:
            String[] arrOfStr = command.split(" ", 2);
            try {
                validateCmd(arrOfStr[1]);
            } catch (MissingDescriptionException e) {
                System.out.println(e.getMessage());
            }
            switch (arrOfStr[0]) {
            case "delete":
                indx = Integer.parseInt(arrOfStr[1]);
                task = this.tasks.delete(indx);
                toReturn = "Noted. I've removed this task:";
                toReturn = toReturn.concat(task.toString());
                toReturn = toReturn + System.lineSeparator() +
                        "Now you have " + this.tasks.size() + " tasks in the list.";
                return toReturn;
            case "unmark":
                indx = Integer.parseInt(arrOfStr[1]);
                task = this.tasks.get(indx);
                this.tasks.setToUnmark(indx);
                toReturn = "OK, I've marked this task as not done yet:\n" + task;
                return toReturn;
            case "mark":
                indx = Integer.parseInt(arrOfStr[1]);
                task = this.tasks.get(indx);
                this.tasks.setToMark(indx);
                toReturn = "Nice! I've marked this task as done:\n" + task;
                return toReturn;
            case "find":
                String toSearch = arrOfStr[1];
                TaskList toPrint = this.tasks.search(toSearch);
                toReturn = "Here are the matching tasks in your list:" + toPrint.printList();
                return toReturn;
            case "todo":
                task = new Todo(arrOfStr[1]);
                toReturn = "Very nice. I've added this task:" + System.lineSeparator() + task
                        + System.lineSeparator();
                this.tasks.add(task);
                toReturn = toReturn + "Now you have " + this.tasks.size() + " tasks in the list.";
                return toReturn;
            case "deadline":
                String[] dl = arrOfStr[1].split("/by ");
                try {
                    validateDate(dl);
                } catch (InvalidCmdException e) {
                    System.out.println(e.getMessage());
                }
                task = new Deadline(dl[0], dl[1]);
                toReturn = "Very nice. I've added this task:" + System.lineSeparator() + task;
                this.tasks.add(task);
                return toReturn;
            case "event":
                String[] ev = arrOfStr[1].split("/from");
                String[] time = ev[1].split("/to");
                task = new Event(ev[0], time[0], time[1]);
                toReturn = "Very nice. I've added this task:" + System.lineSeparator() + task;
                this.tasks.add(task);
                toReturn = toReturn + "Now you have " + this.tasks.size() + " tasks in the list.";
                return toReturn;
            default:
                toReturn = "Sorry your command is invalid";
                return toReturn;
            }
        }
    }

    /**
     * Ensures the validity of User's input command.
     *
     * @param cmd String representation of the command.
     * @throws MissingDescriptionException If User input lacks a body.
     */
    public static void validateCmd(String cmd) throws MissingDescriptionException {
        if (cmd.length() == 0) {
            throw new MissingDescriptionException("You need to " +
                    "be more specific");
        }

    }

    /**
     * Ensures that of User inputs date as necessary for Events and Deadlines
     *
     * @param cmd String array representation of the command.
     * @throws InvalidCmdException If User fails to provide a Date.
     */
    public static void validateDate(String[] cmd) throws InvalidCmdException {
        if (cmd.length == 1) {
            throw new InvalidCmdException("Please specify date.");
        }
    }
}

