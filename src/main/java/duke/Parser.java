package duke;

import duke.exception.InvalidFormatException;
import duke.exception.UnrecognisedCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.time.LocalDate;

public class Parser {
    private final Ui ui;
    private final TaskList tasks;
    protected final DateTimeFormatter inputFormat;
    protected final DateTimeFormatter outputFormat;

    /**
     * Creates an abstraction of a parser to parse all kinds of text strings
     *
     * @param ui For some tasks to print stuff
     * @param tasks Tasklist that will be used to referenced for functions
     * @param inputFormat A DateTimeFormatter to process user inputs
     * @param outputFormat A DateTimeFormatter to format date outputs to users
     */
    public Parser(Ui ui, TaskList tasks, DateTimeFormatter inputFormat, DateTimeFormatter outputFormat) {
        this.ui = ui;
        this.tasks = tasks;
        this.inputFormat = inputFormat;
        this.outputFormat = outputFormat;
    }

    public boolean processInput(String input) throws InvalidFormatException, UnrecognisedCommandException {
        String[] argFrags = input.split(" ", 2);

        Matcher m;

        switch (argFrags[0]) {
        case "bye":
            ui.print("Goodbye. Shutting down.");
            // Break not required as returns ends switch
            return false;
        case "list":
            ui.print(tasks.toStringList());
            break;
        case "mark":
            if (argFrags.length < 2) {
                throw new InvalidFormatException("mark index");
            }
            tasks.setDone(Integer.parseInt(argFrags[1]), true);
            break;
        case "unmark":
            if (argFrags.length < 2) {
                throw new InvalidFormatException("unmark index");
            }
            tasks.setDone(Integer.parseInt(argFrags[1]), false);
            break;
        case "delete":
            if (argFrags.length < 2) {
                throw new InvalidFormatException("delete index");
            }
            tasks.delete(Integer.parseInt(argFrags[1]));
            break;
        case "todo":
            if (argFrags.length < 2) {
                throw Todo.getInvalidFormatException();
            }
            tasks.add(new Todo(argFrags[1]));
            break;
        case "deadline":
            if (argFrags.length < 2) {
                throw Deadline.getInvalidFormatException();
            }
            tasks.add(new Deadline(argFrags[1], this));
            break;
        case "event":
            if (argFrags.length < 2) {
                throw Event.getInvalidFormatException();
            }
            tasks.add(new Event(argFrags[1], this));
            break;
        case "find":
            if (argFrags.length < 2) {
                throw new InvalidFormatException();
            }
            tasks.find(argFrags[1], this);
            break;
        default:
            throw new UnrecognisedCommandException();
        }

        return true;

    }

    public String convertDateToInputFormat(LocalDate foo) {
        return foo.format(inputFormat);
    }

    public String convertDateToOutputFormat(LocalDate foo) {
        return foo.format(outputFormat);
    }

    public DateTimeFormatter getInputFormat() {
        return inputFormat;
    }

    public DateTimeFormatter getOutputFormat() {
        return outputFormat;
    }
}
