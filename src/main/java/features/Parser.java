package features;
import java.io.IOException;
import java.util.Scanner;

import commands.CommandBye;
import commands.CommandDeadline;
import commands.CommandDelete;
import commands.CommandEvent;
import commands.CommandFind;
import commands.CommandList;
import commands.CommandMark;
import commands.CommandToDo;
import commands.CommandUnmark;

/**
 * Handles user input commands.
 */
public class Parser {

    protected Scanner userScan;
    protected TaskList taskList;
    protected boolean loopEnd = false;

    /**
     * Constructs Parser instance.
     * @param userScan Scanner object containing user input.
     * @param taskList List of tasks.
     */
    public Parser(Scanner userScan, TaskList taskList) {
        this.userScan = userScan;
        this.taskList = taskList;
    }

    /**
     * Calls the Command type suitable for the user input.
     * @throws DukeException Thrown by the individual switch cases.
     */
    public void parse() throws DukeException {
        // switch case for future commands
        switch (this.userScan.next()) {
        // loop breaks, ending program if input is "bye"
        case ("bye"):
            new CommandBye().print();
            this.loopEnd = true;
            break;

        // Duke lists out all Tasks.Task names in TaskList when input is "list"
        case ("list"):
            new CommandList().print(this.userScan, this.taskList);
            break;

        // Duke allows user to mark tasks as done when input is "mark"
        case ("mark"):
            this.taskList.clone(new CommandMark().handle(this.userScan, this.taskList));
            break;

        // Duke finds and prints tasks that match user search input
        case ("find"):
            new CommandFind().print(this.userScan, this.taskList);
            break;

        // Duke allows user to mark tasks as NOT done when input is "unmark"
        case ("unmark"):
            this.taskList.clone(new CommandUnmark().handle(this.userScan, this.taskList));
            break;

        // Duke deletes task when input is "delete"
        case ("delete"):
            this.taskList.clone(new CommandDelete().handle(this.userScan, this.taskList));
            break;

        // Duke adds Tasks.Deadline
        case ("deadline"):
            this.taskList.clone(new CommandDeadline().handle(this.userScan, this.taskList));
            break;

        // Duke adds Tasks.Event
        case ("event"):
            this.taskList.clone(new CommandEvent().handle(this.userScan, this.taskList));
            break;

        // Duke adds Tasks.ToDo
        case ("todo"):
            this.taskList.clone(new CommandToDo().handle(this.userScan, this.taskList));
            break;

        // Duke does not understand any other commands (yet).
        default:
            new Ui().print("Yeah, i'm sorry. I don't understand that.");
        }
        autoSave(this.taskList);
    }

    /**
     * Automatically saves state of taskList after each change.
     * @param taskList The taskList to save.
     * @throws DukeException  If the taskList cannot be saved.
     */
    public void autoSave(TaskList taskList) throws DukeException {
        try {
            new Storage().saveTaskList(taskList);
        } catch (IOException err) {
            throw new DukeException(new Ui().formatMessage("[ERROR]\nOops, we couldn't save that!"));
        }
    }

    public boolean updateLoopEnd() {
        return this.loopEnd;
    }

    public TaskList updateTaskList() {
        return this.taskList;
    }
}
