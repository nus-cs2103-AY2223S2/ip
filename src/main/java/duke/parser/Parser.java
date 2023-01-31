package duke.parser;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Parser class
 * To process the data in String format
 */
public class Parser {
    private Scanner userIn;

    /**
     * Constructor method for Parser.
     *
     * @param userIn Scanner object that takes in user input.
     */
    public Parser(Scanner userIn) {
        this.userIn = userIn;
    }

    /**
     * Main method for parsing commands.
     *
     * @param taskList TaskList containing list of all tasks in the program.
     * @param ui ui to show user messages.
     * @throws DukeException if there is an error (usually user input error).
     */
    public void parse_cmds(TaskList taskList, Ui ui) throws DukeException {
        String currIn = userIn.nextLine().trim();
        String[] curr = new String[0];
        if (currIn.contains("/by")) {
            curr = currIn.split("/by"); //split into title and time-related
        } else if (currIn.contains("/from")) {
            curr = currIn.split("/from");
        } else {
            curr = new String[]{currIn};
        }
        String[] currTitle = curr[0].split(" "); //split title by word
        if (currIn.equals("bye")) {
            taskList.end(ui);
        } else if (currIn.equals("list")) {
            ui.print(taskList);
        } else if (currTitle[0].equals("mark")) {
            taskList.mark(currTitle, ui);
        } else if (currTitle[0].equals("unmark")) {
            taskList.unmark(currTitle, ui);
        } else if (currTitle[0].equals("todo")) {
            taskList.addToDo(curr, ui);
        } else if (currTitle[0].equals("deadline")) {
            taskList.addDeadline(curr, ui);
        } else if (currTitle[0].equals("event")) {
            taskList.addEvent(curr, ui);
        } else if (currTitle[0].equals("delete")) {
            taskList.deleteTask(currTitle, ui);
        } else if (currTitle[0].equals("find")) {
            taskList.findTask(currTitle, ui);
        } else {
            throw new DukeException("Hmmm, I don't understand what you want to do");
        }
    }

    /**
     * Process string data and return appropriate command.
     *
     * @param taskList taskList containing tasks
     * @param ui process and return appropriate UI.
     * @return String representing command
     */
    public static String parse(String input, TaskList taskList, Ui ui) throws DukeException {
        String currIn = input;
        String[] curr = new String[0];
        if (currIn.contains("/by")) {
            curr = currIn.split("/by"); //split into title and time-related
        } else if (currIn.contains("/from")) {
            curr = currIn.split("/from");
        } else {
            curr = new String[]{currIn};
        }
        String[] currTitle = curr[0].split(" "); //split title by word
        if (currIn.equals("bye")) {
            return "\t Bye. See you next time! :)\n";
        } else if (currIn.equals("list")) {
            return ui.guiPrint(taskList);
        } else if (currTitle[0].equals("mark")) {
            return taskList.guiMark(currTitle, ui);
        } else if (currTitle[0].equals("unmark")) {
            return taskList.guiUnmark(currTitle, ui);
        } else if (currTitle[0].equals("todo")) {
            return taskList.guiAddToDo(curr, ui);
        } else if (currTitle[0].equals("deadline")) {
            return taskList.guiAddDeadline(curr, ui);
        } else if (currTitle[0].equals("event")) {
            return taskList.guiAddEvent(curr, ui);
        } else if (currTitle[0].equals("delete")) {
            return taskList.guiDeleteTask(currTitle, ui);
        } else if (currTitle[0].equals("find")) {
            return taskList.guiFindTask(currTitle, ui);
        } else {
            throw new DukeException("Hmmm, I don't understand what you want to do");
        }
    }
}
