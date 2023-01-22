import Features.DukeException;
import Features.TaskList;
import Features.Ui;
import UserCommands.*;

import java.io.IOException;
import java.util.Scanner;

public class Parser {

    void parse(Scanner userScan) throws DukeException {
        // switch case for future commands
        switch (userScan.next()) {
            // loop breaks, ending program if input is "bye"
            case ("bye"):
                new CommandBye().print();
                Duke.loopEnd = true;
                break;

            // Duke lists out all Tasks.Task names in TaskList when input is "list"
            case ("list"):
                new CommandList().print(userScan, Duke.taskList);
                break;

            // Duke allows user to mark tasks as done when input is "mark"
            case ("mark"):
                Duke.taskList.clone(new CommandMark().handle(userScan, Duke.taskList));
                break;

            // Duke allows user to mark tasks as NOT done when input is "unmark"
            case ("unmark"):
                Duke.taskList.clone(new CommandUnmark().handle(userScan, Duke.taskList));
                break;

            // Duke deletes task when input is "delete"
            case ("delete"):
                Duke.taskList.clone(new CommandDelete().handle(userScan, Duke.taskList));
                break;

            // Duke adds Tasks.Deadline
            case ("deadline"):
                Duke.taskList.clone(new CommandDeadline().handle(userScan, Duke.taskList));
                break;

            // Duke adds Tasks.Event
            case ("event"):
                Duke.taskList.clone(new CommandEvent().handle(userScan, Duke.taskList));
                break;

            // Duke adds Tasks.ToDo
            case ("todo"):
                Duke.taskList.clone(new CommandToDo().handle(userScan, Duke.taskList));
                break;

            // Duke does not understand any other commands (yet).
            default:
                new Ui().print("Yeah, i'm sorry. I don't understand that.");
        }
        autoSave(Duke.taskList);
    }

    public void autoSave(TaskList taskList) throws DukeException {
        try {
            Duke.dukeSave.saveTaskList(taskList);
        }
        catch (IOException err) {
            throw new DukeException(new Ui().formatMessage("[ERROR]\nOops, we couldn't save that!"));
        }
    }
}
