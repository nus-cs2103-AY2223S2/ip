import DukeExceptions.DukeEmptyInputException;
import DukeExceptions.DukeInvalidInputException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.util.ArrayList;
public class ToDoList {
    private ArrayList<Task> list;

    public ToDoList() {
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
        str.deleteCharAt(str.length() - 1);
        return String.format("Here are the tasks in your list:\n%s", str);
    }

    public String changeState(String param, String action) throws DukeInvalidInputException, DukeEmptyInputException {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(param);
        if (matcher.matches()) {
            throw new DukeInvalidInputException("Sorry you need to specify a single input.");
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
}
