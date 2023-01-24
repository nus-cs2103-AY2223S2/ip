package duke;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TaskList {
    private final List<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    void parseEventFromFile(String[] tokens) {
        String taskType = tokens[0];
        if (Objects.equals(taskType, "T")) {
            tasks.add(new ToDo(tokens[2], tokens[1]));
        } else if (Objects.equals(taskType, "D")) {
            tasks.add(new Deadline(tokens[2], tokens[3], tokens[1]));
        } else if (Objects.equals(taskType, "E")) {
            tasks.add(new Event(tokens[2], tokens[3], tokens[4], tokens[1]));
        }
    }

    private void addToList(Task task, Ui ui) {
        this.tasks.add(task);
        ui.displayMessage("Got it. I've added this task:\n" +
                task.toString() +
                "\nNow you have " +
                tasks.size() +
                " tasks in the list\n");
    }

    void addToDo(String[] tokens, Ui ui) throws DukeException {
        StringBuilder sb = new StringBuilder();
        if (tokens.length < 2) {
            throw new DukeException("The description of a todo cannot be empty");
        }
        for (int i = 1; i < tokens.length; i++) {
            sb.append(tokens[i]).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        ToDo td = new ToDo(sb.toString());
        addToList(td, ui);
    }

    void addDeadline(String[] tokens, Ui ui) throws DukeException {
        StringBuilder sb = new StringBuilder();
        int idxDelimiter = Arrays.asList(tokens).indexOf("/by");
        if (idxDelimiter == -1) {
            throw new DukeException("deadline tasks must be specified by /by [deadline] format");
        } else if (idxDelimiter == tokens.length - 1) {
            throw new DukeException("please specify a deadline after the /by tag");
        } else if (idxDelimiter == 1) {
            throw new DukeException("The description of a deadline cannot be empty");
        } else {
            for (int i = 1; i < idxDelimiter; i++) {
                sb.append(tokens[i]).append(" ");
            }
            String taskName = sb.deleteCharAt(sb.length()-1).toString();
            sb.delete(0, sb.length());
            for (int i = idxDelimiter + 1; i < tokens.length; i++) {
                sb.append(tokens[i]).append(" ");
            }
            String dueDate = sb.deleteCharAt(sb.length()-1).toString();
            try {
                Task task = new Deadline(taskName, dueDate);
                addToList(task, ui);
            } catch (DateTimeParseException e) {
                ui.displayMessage("Please enter a valid date in the format YYYY-MM-DD/HH:mm\n");
            }
        }
    }

    void addEvent(String[] tokens, Ui ui) throws DukeException {
        StringBuilder sb = new StringBuilder();
        int idxFrom = Arrays.asList(tokens).indexOf("/from");
        int idxTo = Arrays.asList(tokens).indexOf("/to");
        if (idxFrom == -1 || idxTo == -1) {
            throw new DukeException("event tasks must be specified by a /from [start] /to [end] format");
        } else if (idxFrom > idxTo) {
            throw new DukeException("/to flag must come after /from flag");
        } else if (idxFrom == 1) {
            throw new DukeException("The description of a event task cannot be empty");
        } else if (idxTo - idxFrom == 1) {
            throw new DukeException("please specify a start datetime after /from flag");
        } else if (tokens.length - 1 == idxTo) {
            throw new DukeException("please specify an end datetime after /to flag");
        } else {
            for (int i = 1; i < idxFrom; i++) {
                sb.append(tokens[i]).append(" ");
            }
            String taskName = sb.deleteCharAt(sb.length()-1).toString();
            sb.delete(0, sb.length());

            for (int i = idxFrom + 1; i < idxTo; i++) {
                sb.append(tokens[i]).append(" ");
            }
            String taskFrom = sb.deleteCharAt(sb.length()-1).toString();
            sb.delete(0, sb.length());

            for (int i = idxTo + 1; i < tokens.length; i++) {
                sb.append(tokens[i]).append(" ");
            }
            String taskTo = sb.deleteCharAt(sb.length()-1).toString();
            try {
                Task task = new Event(taskName, taskFrom, taskTo);
                addToList(task, ui);
            } catch (DateTimeParseException e) {
                ui.displayMessage("Please enter valid dates in the format YYYY-MM-DD/HH:mm\n");
            }

        }
    }

    void markListItem(String[] tokens, Ui ui) {
        try {
            int listIndex = Integer.parseInt(tokens[1])-1;
            tasks.get(listIndex).setStatus("X");
            ui.displayMessage("Nice! I've marked this task as done:\n" +
                    tasks.get(listIndex).toString() + "\n");
        } catch (NumberFormatException e) {
            ui.displayMessage("Please specify a numerical task index to mark\n");
        } catch (IndexOutOfBoundsException e) {
            ui.displayMessage("Please specify a valid index to mark\n");
        }
    }

    void unmarkListItem(String[] tokens, Ui ui) {
        try {
            int listIndex = Integer.parseInt(tokens[1]) - 1;
            tasks.get(listIndex).setStatus(" ");
            ui.displayMessage("OK, I've marked this task as not done yet:\n" +
                    tasks.get(listIndex).toString() + "\n");
        } catch (NumberFormatException e) {
            ui.displayMessage("Please specify a numerical task index to unmark\n");
        } catch (IndexOutOfBoundsException e) {
            ui.displayMessage("Please specify a valid index to unmark\n");
        }
    }

    void deleteItem(String[] tokens, Ui ui) throws DukeException {
        if (tokens.length != 2) {
            throw new DukeException("please specify delete command as delete [list index]");
        } else if (tasks.size() == 0) {
            throw new DukeException("Task list is empty");
        }
        try {
            int listIndex = Integer.parseInt(tokens[1]) - 1;
            Task removed = tasks.remove(listIndex);
            ui.displayMessage("Noted. I've removed this task:\n" +
                    removed.toString() +
                    "\nNow you have " + tasks.size() + " tasks in the list\n");
        } catch (NumberFormatException e) {
            ui.displayMessage("please specify a valid number to delete entry\n");
        } catch (IndexOutOfBoundsException e) {
            ui.displayMessage("please specify a valid index to delete\n");
        }
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }
}
