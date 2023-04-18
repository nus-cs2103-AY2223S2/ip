package seedu.duke.parser;

import seedu.duke.commands.*;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeMarkOutOfBounds;
import seedu.duke.exceptions.DukeTodoNoDescription;
import seedu.duke.ui.Ui;

public class CommandParser {
    private Ui ui;
    public Command parse(String rawInput) {
        String[] input = rawInput.trim().split(" ", 2);
        String command = input[0].toLowerCase();
        String arguments;
        try {
            arguments = input[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            arguments = "";
        }
        switch (command) {
            case "echo": {
                Command echo = new Echo(arguments);
                return echo;
            }
            case "list": {
                Command list = new ListTasks();
                return list;
            }
            case "mark": {
                int index = Integer.parseInt(arguments) - 1;
                try {
                    Command mark = new Mark(index);
                    return mark;

                } catch (DukeMarkOutOfBounds e) {
                    System.out.println(e.getMessage());
                }
            }
            // honestly creation of task objects here should be handled by the task objects
            // change later
            case "todo": {
                try {
                    Command addTask = new AddTask("todo", arguments);
                    return addTask;
                } catch (DukeTodoNoDescription e) {
                    ui.showErrorMessage(e);
                }
            }
            case "deadline": {
                try {
                    Command addTask = new AddTask("deadline", arguments);
                    return addTask;
                } catch (DukeException e) {
                    ui.showErrorMessage(e);
                }
                break;
            }
            case "event": {
                try {
                    Command addTask = new AddTask("event", arguments);
                    return addTask;
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case "delete": {
                Command deleteTask = new DeleteTask(Integer.parseInt(arguments));
                return deleteTask;
            }
            case "save": {
                Command save = new Save();
                return save;
            }
            case "find": {
                Command find = new Find(arguments);
                return find;
            }
            case "bye": {
                Command exit = new Exit();
                return exit;
            }
            default: {
                Command unknown = new Default();
                return unknown;
            }
        }
        return new Default();
    }

    public CommandParser(Ui ui){
        this.ui = ui;
    }
}
