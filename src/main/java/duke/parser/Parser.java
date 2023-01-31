package duke.parser;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.storage.Storage;
import duke.ui.Ui;


public class Parser {
    private Storage storage;
    private Ui ui;

    public Parser(Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
    }

    public boolean parse(String input) {
        String cmd;

        try{
            String[] inputs = input.split(" ", 2);
            cmd = inputs[0];

            int idx;
            Task tsk;
            String markMsg, tskNum;
            String cfmMsg = "Got it. I've added this task:";
            String[] desc;
            LocalDateTime date, from, to;

            TaskList data = this.storage.loadTasks();

            switch (cmd) {
                case "bye":
                    this.ui.printMsg("Bye. Hope to see you again soon!");
                    return false;
                case "list":
                    this.ui.printList(data.toStringArray());
                    break;
                case "mark":
                    idx = Integer.parseInt(inputs[1]) - 1;
                    tsk = data.get(idx);
                    tsk.mark();
                    markMsg = "Nice! I've marked this task as done:";
                    String markedTask = String.format(" %s", tsk);
                    String[] markedMsg = {markMsg, markedTask};
                    this.ui.printMsg(markedMsg);
                    break;
                case "unmark":
                    idx = Integer.parseInt(inputs[1]) - 1;
                    tsk = data.get(idx);
                    tsk.unmark();
                    markMsg = "OK, I've marked this task as not done yet:";
                    String unmarkedTask = String.format(" %s", tsk);
                    String[] unmarkedMsg = {markMsg, unmarkedTask};
                    this.ui.printMsg(unmarkedMsg);
                    break;
                case "todo":
                    if (inputs.length == 1)
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    tsk = new Todo(inputs[1]);
                    data.add(tsk);
                    tskNum = String.format("Now you have %d tasks in the list.", data.size());
                    String[] todoMsg = {cfmMsg, " " + tsk.toString(), tskNum};
                    this.ui.printMsg(todoMsg);
                    break;
                case "deadline":
                    desc = inputs[1].split(" /by ");
                    date = parseDate(desc[1]);
                    tsk = new Deadline(desc[0], date);
                    data.add(tsk);
                    tskNum = String.format("Now you have %d tasks in the list.", data.size());
                    String[] deadlineMsg = {cfmMsg, " " + tsk.toString(), tskNum};
                    this.ui.printMsg(deadlineMsg);
                    break;
                case "event":
                    desc = inputs[1].split(" /from | /to ");
                    from = parseDate(desc[1]);
                    to = parseDate(desc[2]);
                    tsk = new Event(desc[0], from, to);
                    data.add(tsk);
                    tskNum = String.format("Now you have %d tasks in the list.", data.size());
                    String[] eventMsg = {cfmMsg, " " + tsk.toString(), tskNum};
                    this.ui.printMsg(eventMsg);
                    break;
                case "delete":
                    idx = Integer.parseInt(inputs[1]) - 1;
                    tsk = data.get(idx);
                    data.delete(idx);
                    tskNum = String.format("Now you have %d tasks in the list.", data.size());
                    String[] delMsg = {"Noted. I've removed this task:", tsk.toString(), tskNum};
                    this.ui.printMsg(delMsg);
                    break;
                case "find":
                    String query = inputs[1];
                    String[] taskStrList = data.find(query);
                    if (taskStrList.length == 0) {
                        this.ui.printMsg("Sorry, I could not find any matches.");
                    } else {
                        this.ui.printList("Here are the matching tasks in your list: ", data.find(query));
                    }
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

            }
            this.storage.saveTasks(data);
        } catch (DukeException | IllegalArgumentException e) {
            this.ui.printMsg(e.getMessage());
        }
        return true;
    }

    private LocalDateTime parseDate(String dateString) {
        LocalDateTime date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
        try {
            date = LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateString + ". Please give your date"
                    + " in the form M/d/yyyy HHmm.");
        }
        return date;
    }

}
