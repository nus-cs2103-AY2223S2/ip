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
    public final DateTimeFormatter inputFormat;
    public final DateTimeFormatter outputFormat;

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
            return false;
        // Break not required as returns ends switch
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
}
