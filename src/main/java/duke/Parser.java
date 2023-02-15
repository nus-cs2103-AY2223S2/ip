package duke;

import duke.command.*;
import duke.gui.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Parser class to handle all the user input commands.
 */
public class Parser {

    private Ui ui;
    private StringTokenizer tk;
    private String fullCommand;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy:HHmm");

    /**
     * Constructor
     */
    public Parser() {

    }


    /**
     * Convert user input into command and provide validation of the user input
     *
     * @param fullCommand = User input
     * @param ui = User interface class
     * @return = return the command Duke thinks from the User Input
     */

    public Command parse(String fullCommand, Ui ui) {
        this.fullCommand = fullCommand;
        this.tk = new StringTokenizer(fullCommand);
        this.ui = ui;

        String action = nextString();
        Command c = null;
        Task t = null;

        switch (action) {
        case "list":
            c = new ListCommand();
            break;
        case "mark": {
            int index = getIndex();
            c = new MarkCommand(index);
            break;
        }
        case "delete": {
            int index = getIndex();
            c = new DeleteCommand(index);
            break;
        }
        case "find": {
            String value = nextString();
            if (value != null) {
                c = new FindCommand(value);
            }
            break;
        }
        case "todo": {
            c = todoHelper(c,t);
            break;
        }
        case "deadline": {
            c = deadlineHelper(c,t);
            break;
        }
        case "event": {
            c = eventHelper(c,t);
            break;
        }
        case "archive": {
            c = new ArchiveCommand();
            break;
        }
        case "exit": {
            c = new ExitCommand();
            break;
        }
        default:
            c = new UnknownCommand();
            break;
        }
        //Assertion Check
        assert c == null: "Command c should not be null";
        return c;
    }

    /**
     * A helper method to parse todo
     * @return ErrorCommand or AddCommand
     */
    public Command todoHelper(Command c, Task t) {
        String value = getToDo();
        if (value != null && !value.isEmpty())  {
            t = new Todo(value, false);
            c = new AddCommand(t);
        } else {
            c = new ErrorCommand(ErrorCommand.types.emptyDesc,"todo");
        }
        return c;
    }
    /**
     * A helper method to parse Deadline
     * @return ErrorCommand or AddCommand
     */
    public Command deadlineHelper(Command c,Task t) {
        String input;
        String value = "";
        String date ;
        boolean isBy = false; // Check whether the user has typed /by in his command

        while ((input = nextString()) != null) {
            if (input.equals("/by")) {
                date = nextString();
                isBy = true;
                LocalDateTime dateTime = stringToDateTime(date);
                if(dateTime != null) {
                    t = new Deadline(value, dateTime, false);
                    c = new AddCommand(t);
                } else {
                    c = new ErrorCommand(ErrorCommand.types.dateTime,"deadline");
                }
                break;
            } else
                value = value + " " +  input;
        }

        if(value == "") {
            c = new ErrorCommand(ErrorCommand.types.emptyDesc,"deadline");
        } else if (isBy == false) {
            c = new ErrorCommand(ErrorCommand.types.missingBy,"deadline");
        }
        return c;
    }

    /**
     * A helper method to parse Event
     * @return ErrorCommand or AddCommand
     */
    public Command eventHelper(Command c,Task t) {
        String input;
        String value = "";
        String from;
        String to ;
        boolean isFrom = false;

        while ((input = nextString()) != null) {
            if (input.equals("/from")) {
                from = nextString();
                LocalDateTime dateTime;
                isFrom = true;
                dateTime = stringToDateTime(from);
                if(dateTime == null) {
                    c = new ErrorCommand(ErrorCommand.types.dateTime,"event from");
                    break;
                }
                String isTo = nextString();
                if(!(isTo != null && isTo.equals("/to"))) {
                    c = new ErrorCommand(ErrorCommand.types.missingTo,"event");
                    break;
                }
                to = nextString();
                LocalDateTime dateTime2;
                dateTime2 = stringToDateTime(to);
                if(dateTime2 == null) {
                    c = new ErrorCommand(ErrorCommand.types.dateTime,"event to");
                    break;
                }
                t = new Event(value, dateTime, dateTime2, false);
                c = new AddCommand(t);
                break;
            } else
                value = value + " " + input;
        }

        if(value == "") {
            c = new ErrorCommand(ErrorCommand.types.emptyDesc,"event");
        } else if (isFrom == false) {
            c = new ErrorCommand(ErrorCommand.types.missingFrom,"event");
        }
        return c;
    }

    //try and catch for tk.nextToken() for integer;
    public Integer getIndex() {
        try {
            return Integer.parseInt(this.tk.nextToken());
        } catch(NoSuchElementException | NumberFormatException e) {
            ui.showError(1);
        }
        return null;
    }

    //try and catch for tk.nextToken() for string
    public String nextString() {
        try {
            return tk.nextToken();
        } catch (NoSuchElementException e) {
            ui.showError(1);
        }
        return null;
    }

    //Custom method for todoHelper()
    public String getToDo() {
        String[] arr = fullCommand.split(" ", 2);
        try {
            return arr[1].trim();
        } catch(ArrayIndexOutOfBoundsException e)  {
            return null;
        }
    }

    //Convert String to datetime format
    public LocalDateTime stringToDateTime(String x) {
        LocalDateTime dt;
        try {
            dt = LocalDateTime.parse(x, formatter);
        } catch (DateTimeParseException | NullPointerException e) {
            return null;
        }
        return dt;
    }

}
