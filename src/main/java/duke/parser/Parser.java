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
    private Scanner usr_in;

    /**
     * Constructor method for Parser.
     *
     * @param usr_in Scanner object that takes in user input.
     */
    public Parser(Scanner usr_in) {
        this.usr_in = usr_in;
    }

    /**
     * Main method for parsing commands.
     *
     * @param taskList TaskList containing list of all tasks in the program.
     * @param ui ui to show user messages.
     * @throws DukeException if there is an error (usually user input error).
     */
    public void parse_cmds(TaskList taskList, Ui ui) throws DukeException {
        String curr_in = usr_in.nextLine().trim();
        String[] curr = new String[0];
        if (curr_in.contains("/by")) {
            curr = curr_in.split("/by"); //split into title and time-related
        } else if (curr_in.contains("/from")) {
            curr = curr_in.split("/from");
        } else {
            curr = new String[]{curr_in};
        }
        String[] curr_title = curr[0].split(" "); //split title by word
        if (curr_in.equals("bye")) {
            taskList.end(ui);
        } else if (curr_in.equals("list")) {
            ui.print(taskList);
        } else if (curr_title[0].equals("mark")) {
            taskList.mark(curr_title, ui);
        } else if (curr_title[0].equals("unmark")) {
            taskList.unmark(curr_title, ui);
        } else if (curr_title[0].equals("todo")) {
            taskList.addToDo(curr, ui);
        } else if (curr_title[0].equals("deadline")) {
            taskList.addDeadline(curr, ui);
        } else if (curr_title[0].equals("event")) {
            taskList.addEvent(curr, ui);
        } else if (curr_title[0].equals("delete")) {
            taskList.deleteTask(curr_title, ui);
        } else if (curr_title[0].equals("find")) {
            taskList.findTask(curr_title, ui);
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
        String curr_in = input;
        String[] curr = new String[0];
        if (curr_in.contains("/by")) {
            curr = curr_in.split("/by"); //split into title and time-related
        } else if (curr_in.contains("/from")) {
            curr = curr_in.split("/from");
        } else {
            curr = new String[]{curr_in};
        }
        String[] curr_title = curr[0].split(" "); //split title by word
        if (curr_in.equals("bye")) {
            return "\t Bye. See you next time! :)\n";
        } else if (curr_in.equals("list")) {
            return ui.guiPrint(taskList);
        } else if (curr_title[0].equals("mark")) {
            return taskList.guiMark(curr_title, ui);
        } else if (curr_title[0].equals("unmark")) {
            return taskList.guiUnmark(curr_title, ui);
        } else if (curr_title[0].equals("todo")) {
            return taskList.guiAddToDo(curr, ui);
        } else if (curr_title[0].equals("deadline")) {
            return taskList.guiAddDeadline(curr, ui);
        } else if (curr_title[0].equals("event")) {
            return taskList.guiAddEvent(curr, ui);
        } else if (curr_title[0].equals("delete")) {
            return taskList.guiDeleteTask(curr_title, ui);
        } else if (curr_title[0].equals("find")) {
            return taskList.guiFindTask(curr_title, ui);
        } else {
            throw new DukeException("Hmmm, I don't understand what you want to do");
        }
    }
}
