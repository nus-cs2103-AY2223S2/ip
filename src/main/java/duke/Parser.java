package duke;

import duke.command.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Parser {

    private Ui ui;
    private StringTokenizer tk;
    private String fullCommand;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyyHHmm");
    public Parser() {

    }

    public Command parse(String fullCommand, Ui ui) {
        this.fullCommand = fullCommand;
        this.tk = new StringTokenizer(fullCommand);
        this.ui = ui;

        String action = tk.nextToken();
        Command c = null;
        Task t;

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
        case "todo": {
            String value = getToDo();
            if (value != null) {
                t = new Todo(value, false);
                c = new AddCommand(t);
            }
            break;
        }
        case "deadline": {
            String nextString;
            String value = "";
            String date ;
            while ((nextString = getString()) != null) {
                if (nextString.equals("/by")) {
                    date = getString() + getString();
                    LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
                    t = new Deadline(value, dateTime, false);
                    c = new AddCommand(t);
                    break;
                } else
                    value = value + nextString;
            }
            break;
        }
        case "event": {
            String nextString;
            String value = "";
            String from;
            String to ;
            while ((nextString = getString()) != null) {
                if (nextString.equals("/from")) {
                    from = getString() + getString();
                    getString();
                    to = getString() + getString();
                    LocalDateTime dateTime = LocalDateTime.parse(from, formatter);
                    LocalDateTime dateTime2 = LocalDateTime.parse(to, formatter);
                    t = new Event(value, dateTime, dateTime2, false);
                    c = new AddCommand(t);
                    break;
                } else
                    value = value + nextString;
            }
            break;
        }
        default:
            c = new CommandNotFound();
            break;
        }

        return c;
    }

    public Integer getIndex() {
        try {
            return Integer.parseInt(this.tk.nextToken());
        } catch(NoSuchElementException | NumberFormatException e) {
            ui.showError(1);
        }
        return null;
    }

    //Custom method for ToDo action
    public String getToDo() {
        String[] arr = fullCommand.split(" ", 2);
        try {
            return arr[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            ui.customMessage("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return null;
    }

    public String getString() {
        try {
            return tk.nextToken();
        } catch (NoSuchElementException e) {
            ui.showError(1);
        }
        return null;
    }

}
