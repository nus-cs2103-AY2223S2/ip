package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;

public class Parser {
    private Scanner sc;
    private boolean isDone;

    public Parser(Scanner sc) {
        this.sc = sc;
        this.isDone = false;
    }

    public TaskList parse(TaskList tasks) throws DukeException {
        String input = sc.nextLine();
        if ("bye".equals(input)) {
            isDone = true;
            return tasks;
        } else if ("list".equals(input)) {
            Ui.listMessage(tasks);
        } else if (isMark(input, tasks.size())) {
            int taskindex = Integer.parseInt(input.substring(5)) - 1;
            tasks.get(taskindex).markAsDone();
            Ui.markMessage(tasks.get(taskindex));
        } else if (isUnMark(input, tasks.size())) {
            int taskindex = Integer.parseInt(input.substring(7)) - 1;
            tasks.get(taskindex).markAsNotDone();
            Ui.unmarkMessage(tasks.get(taskindex));
        } else if (isDelete(input, tasks.size())) {
            int taskindex = Integer.parseInt(input.substring(7)) - 1;
            tasks.remove(taskindex);
            Ui.deleteMessage(tasks.get(taskindex));
        } else { //Task Creation
            Task task = null;
            if (isToDo(input)) {
                task = new ToDo(input);
                tasks.add(task);
            } else if (isDeadline(input)) {
                int index = input.indexOf(" /by ");
                task = new Deadline(input.substring(0, index), input.substring(index + 5));
                tasks.add(task);
            } else if (isEvent(input)) {
                int fromdex = input.indexOf(" /from ");
                int todex = input.indexOf(" /to ");
                task = new Event(input.substring(0, fromdex), input.substring(fromdex + 7, todex), input.substring(todex + 5));
                tasks.add(task);
            } else {
                throw new DukeException("Please input a task with either todo, deadline or event prefixed!");
            }
            Ui.addMessage(task, tasks.size());
        }
        return tasks;
    }

    public boolean notDone() {
        return !isDone;
    }

    public boolean isMark(String input, int listSize) {
        if (input.length() >=  6 && input.startsWith("mark ") && isNumeric(input.substring(5))) {
            int taskindex = Integer.parseInt(input.substring(5)) - 1;
            return !(taskindex < 0 || taskindex > listSize - 1);
        }
        return false;
    }

    public boolean isUnMark(String input, int listSize) {
        if (input.length() >=  8 && input.startsWith("unmark ") && isNumeric(input.substring(7))) {
            int taskindex = Integer.parseInt(input.substring(7)) - 1;
            return !(taskindex < 0 || taskindex > listSize - 1);
        }
        return false;
    }

    public boolean isDelete(String input, int listSize) {
        if (input.length() >=  8 && input.startsWith("delete ") && isNumeric(input.substring(7))) {
            int taskindex = Integer.parseInt(input.substring(7)) - 1;
            return !(taskindex < 0 || taskindex > listSize - 1);
        }
        return false;
    }

    public boolean isToDo(String input) throws DukeException {
        if (input.length() >= 4 && input.startsWith("todo")) {
            if (input.equals("todo") || input.substring(4).isBlank()) {
                throw new DukeException("TODO needs a description!");
            } else return input.startsWith("todo ");
        }
        return false;
    }

    public boolean isDeadline(String input) throws DukeException {
        if (input.length() >= 8 && input.startsWith("deadline")) {
            if (input.equals("deadline") || input.substring(8).isBlank() || input.equals("deadline /by") || input.equals("deadline /by ")) {
                throw new DukeException("DEADLINE needs a description!");
            } else return input.contains(" /by ");
        }
        return false;
    }

    public boolean isEvent(String input) throws DukeException {

        if (input.length() >= 5 && input.startsWith("event")) {
            if (input.equals("event") || input.substring(5).isBlank()) {
                throw new DukeException("EVENT needs a description!");
            }
            int fromdex = input.indexOf(" /from ");
            int todex = input.indexOf(" /to ");
            if (fromdex + 7 > todex) {
                throw new DukeException("What are you saying?");
            }
            return true;
        }
        return false;
    }
    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }
}
