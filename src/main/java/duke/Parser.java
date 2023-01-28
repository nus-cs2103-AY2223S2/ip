package duke;

import duke.command.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Parser {

    private Ui ui;
    private StringTokenizer tk;
    private String fullcommand;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyyHHmm");
    public Parser() {

    }

    public Command parse(String fullcommand, Ui ui) {
        this.fullcommand = fullcommand;
        this.tk = new StringTokenizer(fullcommand);
        this.ui = ui;

        String action = nextString();
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
            case "find": {
                String value = nextString();
                if (value != null) {
                    c = new FindCommand(value);
                }
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
                String input;
                String value = "";
                String date ;
                while ((input = nextString()) != null) {
                    if (input.equals("/by")) {
                        date = nextString() + nextString();
                        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
                        t = new Deadline(value, dateTime, false);
                        c = new AddCommand(t);
                        break;
                    } else
                        value = value + input;
                }

                break;
            }
            case "event": {
                String input;
                String value = "";
                String from;
                String to ;
                while ((input = nextString()) != null) {
                    if (input.equals("/from")) {
                        from = nextString() + nextString();
                        nextString();
                        to = nextString() + nextString();
                        LocalDateTime dateTime = LocalDateTime.parse(from, formatter);
                        LocalDateTime dateTime2 = LocalDateTime.parse(to, formatter);
                        t = new Event(value, dateTime, dateTime2, false);
                        c = new AddCommand(t);
                        break;
                    } else
                        value = value + input;
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
        String[] arr = fullcommand.split(" ", 2);
        try {
            return arr[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            ui.customMessage("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return null;
    }

    public String nextString() {
        try {
            return tk.nextToken();
        } catch (NoSuchElementException e) {
            ui.showError(1);
        }
        return null;
    }

}
