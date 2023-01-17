package duke.parser;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

public class Parser {
    private Scanner usr_in;

    public Parser(Scanner usr_in) {
        this.usr_in = usr_in;
    }

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
        } else {
            throw new DukeException("Hmmm, I don't understand what you want to do");
        }
    }
}
