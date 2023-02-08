package duke;

import duke.exception.InvalidFormatException;
import duke.exception.UnrecognisedCommandException;
import duke.gui.Gui;
import duke.gui.Main;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import javafx.application.Application;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {
    private final Parser parser;
    private final TaskList tasks;

    public Duke() throws IOException, InvalidFormatException {
        parser = new Parser("yyyy-MM-dd", "dd-MMM-yyyy (EEE)");
        tasks = new TaskList(new Storage("data", "duke.txt"));
        tasks.loadFromStorage(parser);
    }

    public void run(String[] args) {
        Gui.setDuke(this);
        Application.launch(Main.class, args);
    }

    public String getResponse(String userInput) {
        String[] inputFragments = userInput.split(" ", 2);
        try {
            switch (inputFragments[0]) {
            case "bye":
                return ("To exit, click the X button on the top right of the UI");
            case "list":
                return tasks.toString();
            case "mark":
                if (inputFragments.length < 2) {
                    throw new InvalidFormatException("mark index");
                } else {
                    return tasks.setDone(
                            Integer.parseInt(inputFragments[1]),
                            true,
                            "Nice, I've marked this task as done:");
                }
            case "unmark":
                if (inputFragments.length < 2) {
                    throw new InvalidFormatException("unmark index");
                } else {
                    return tasks.setDone(
                            Integer.parseInt(inputFragments[1]),
                            false,
                            "Ok, I've marked this task as not done:");
                }
            case "delete":
                if (inputFragments.length < 2) {
                    throw new InvalidFormatException("delete index");
                } else {
                    return tasks.delete(
                            Integer.parseInt(inputFragments[1]),
                            "Noted. I've removed this task:");
                }
            case "find":
                if (inputFragments.length < 2) {
                    throw new InvalidFormatException();
                }
                return tasks.find(inputFragments[1]);
            case "todo":
                if (inputFragments.length < 2) {
                    throw Todo.getInvalidFormatException();
                } else {
                    return tasks.add(new Todo(inputFragments[1]));
                }
            case "deadline":
                if (inputFragments.length < 2) {
                    throw Deadline.getInvalidFormatException();
                } else {
                    return tasks.add(
                            Deadline.factoryMethod(inputFragments[1], parser, false)
                    );
                }
            case "event":
                if (inputFragments.length < 2) {
                    throw Event.getInvalidFormatException();
                } else {
                    return tasks.add(
                            Event.factoryMethod(inputFragments[1], parser, false)
                    );
                }
            default:
                throw new UnrecognisedCommandException();
            }
        } catch (InvalidFormatException exception) {
            return exception.getMessage();
        } catch (UnrecognisedCommandException exception) {
            return "Command not recognised. Please try again.";
        } catch (DateTimeParseException exception) {
            return "Wrong date time format, it should be yyyy-MM-dd";
        }
    }

    public static void main(String[] args) {
        try {
            new Duke().run(args);
        } catch (IOException | InvalidFormatException exception) {
            System.out.println(exception.getMessage());
        }
    }
}