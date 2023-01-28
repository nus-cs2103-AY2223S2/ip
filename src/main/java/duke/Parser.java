package duke;

import duke.exception.InvalidFormatException;
import duke.exception.UnrecognisedCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.util.regex.Matcher;

public class Parser {
    private final Ui ui;
    private final TaskList tasks;

    public Parser(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    public boolean processInput(String input) {
        String[] argFrags = input.split(" ", 2);

        Matcher m;

        try {
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
                        throw new InvalidFormatException();
                    }
                    tasks.setDone(Integer.parseInt(argFrags[1]), true);
                    break;
                case "unmark":
                    if (argFrags.length < 2) {
                        throw new InvalidFormatException();
                    }
                    tasks.setDone(Integer.parseInt(argFrags[1]), false);
                    break;
                case "delete":
                    if (argFrags.length < 2) {
                        throw new InvalidFormatException();
                    }
                    tasks.delete(Integer.parseInt(argFrags[1]));
                    break;
                case "todo":
                    if (argFrags.length < 2) {
                        throw new InvalidFormatException();
                    }
                    tasks.add(new Todo(argFrags[1]));
                    break;
                case "deadline":
                    if (argFrags.length < 2) {
                        throw new InvalidFormatException();
                    }
                    m = Deadline.p.matcher(argFrags[1]);
                    if (!m.find()) {
                        throw new InvalidFormatException();
                    }
                    tasks.add(new Deadline(m.group(1), m.group(2)));
                    break;
                case "event":
                    if (argFrags.length < 2) {
                        throw new InvalidFormatException();
                    }
                    m = Event.p.matcher(argFrags[1]);
                    if (!m.find()) {
                        throw new InvalidFormatException();
                    }
                    tasks.add(new Event(m.group(1), m.group(2), m.group(3)));
                    break;
                default:
                    throw new UnrecognisedCommandException();
            }
        } catch (InvalidFormatException e) {
            ui.print("I recognise that keyword, but the format is wrong.");
        } catch (UnrecognisedCommandException e) {
            ui.print("Command not recognised. Please try again.");
        }
        return false;
    }

}
