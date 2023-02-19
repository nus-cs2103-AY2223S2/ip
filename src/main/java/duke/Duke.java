package duke;

import duke.exception.InvalidFormatException;
import duke.exception.LogFileLoadException;
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
    private static final String BYE_MSG = "To exit, click the X button on the top right of the UI";
    private static final String CMD_NOT_FOUND_MSG = "Command not recognised. Please try again.";
    private static final String WRONG_DT_FORMAT_MSG = "Wrong date time format, it should be yyyy-MM-dd";
    private final Parser parser;
    private final TaskList tasks;

    public Duke() throws IOException, LogFileLoadException {
        parser = new Parser("yyyy-MM-dd", "dd-MMM-yyyy (EEE)");
        tasks = new TaskList(
                parser,
                new Storage("data", "duke.txt"));
        tasks.loadFromStorage();
    }

    public void run(String[] args) {
        Gui.setDuke(this);
        Application.launch(Main.class, args);
    }

    public String getResponse(String userInput) {
        assert userInput != null : "userInput cannot be null";
        String[] inputFragments = userInput.split(" ", 2);
        try {
            switch (inputFragments[0]) {
            case "bye":
                return BYE_MSG;
            case "list":
                return tasks.toString();
            case "mark":
                if (inputFragments.length < 2) {
                    throw new InvalidFormatException("mark index");
                } else {
                    return tasks.setDone(
                            Integer.parseInt(inputFragments[1]));
                }
            case "unmark":
                if (inputFragments.length < 2) {
                    throw new InvalidFormatException("unmark index");
                } else {
                    return tasks.setNotDone(
                            Integer.parseInt(inputFragments[1]));
                }
            case "delete":
                if (inputFragments.length < 2) {
                    throw new InvalidFormatException("delete index");
                } else {
                    return tasks.delete(
                            Integer.parseInt(inputFragments[1]));
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
            return CMD_NOT_FOUND_MSG;
        } catch (DateTimeParseException exception) {
            return WRONG_DT_FORMAT_MSG;
        }
    }

    public static void main(String[] args) {
        try {
            new Duke().run(args);
        } catch (IOException | LogFileLoadException exception) {
            System.out.println(exception.getMessage());
        }
    }
}