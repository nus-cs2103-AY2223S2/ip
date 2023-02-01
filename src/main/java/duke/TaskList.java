package duke;

import duke.exceptions.DukeEmptyInputException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidInputException;
import duke.exceptions.DukeTooManyInputsException;
import duke.tasks.Task;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.StringBuilder;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public String add(Task t) {
        list.add(t);
        int size = list.size();
        return String.format("Sure no problem. I've added this task:\n\t%s\nNow you have %d task%s in the list",
                t.toString(), size, size == 1 ? "" : "s");
    }

    public String listItems() {
        StringBuilder str = new StringBuilder();
        int count = 1;
        for (Task t : list) {
            str.append(String.format("%d. %s\n", count, t.toString()));
            count++;
        }
        if (str.length() == 0) {
            return "You currently have no items in the list. Add some tasks to see your list!";
        }
        str.deleteCharAt(str.length() - 1);
        return String.format("Here are the tasks in your list:\n%s", str);
    }

    public String changeState(String param, String action) throws DukeInvalidInputException, DukeEmptyInputException {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(param.trim());
        if (matcher.find()) {
            throw new DukeTooManyInputsException();
        }
        try {
            int index = Integer.parseInt(param.trim());
            if (action.equals("mark")) {
                return list.get(index - 1).markAsDone();
            } else {
                return list.get(index - 1).markNotDone();
            }
        } catch (NumberFormatException e) {
            throw new DukeInvalidInputException(String.format("I'd love to %s that but I only understand numbers. ", action) +
                    "Try inputting a number instead!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidInputException(String.format("You gave me an invalid number to %s. ", action) +
                    "Type list to see what you have in the list so far.");
        }
    }

    public String delete(String input) throws DukeInvalidInputException {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(input.trim());
        if (matcher.find()) {
            throw new DukeTooManyInputsException();
        }
        try {
            int index = Integer.parseInt(input.trim());
            Task removed = list.remove(index - 1);
            int size = list.size();
            return String.format("Sure thing. This task has been deleted:\n\t%s\nNow you have %d task%s in the list",
                    removed, size, size == 1 ? "" : "s");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidInputException("I can't delete what doesn't exist. " +
                    "Type list to see what you have in the list so far.");
        } catch (NumberFormatException e) {
            throw new DukeInvalidInputException("Sorry but I can only delete it if you give me its number. " +
                    "Try inputting a number instead!");
        }
    }

    public String listToText() {
        StringBuilder textString = new StringBuilder();
        for (Task t : list) {
            textString.append(t.toText());
        }
        return textString.toString();
    }

    public void loadTask(String taskText) {
        try {
            list.add(Task.fromText(taskText));
        } catch (DukeException e) {
            System.out.println("Text is formatted wrongly");
        }
    }

    public String find(String keywords) throws DukeEmptyInputException {
        if (keywords.trim().equals("")) {
            throw new DukeEmptyInputException();
        }

        StringBuilder str = new StringBuilder();
        int count = 1;
        for (Task t : list) {
            if (t.getName().contains(keywords)) {
                str.append(String.format("%d. %s\n", count, t));
                count++;
            }
        }
        if (str.length() == 0) {
            return "It appears that you have no matching tasks!";
        }
        str.deleteCharAt(str.length() - 1);
        return "These are the matching tasks that I have found:\n" + str;
    }
}
