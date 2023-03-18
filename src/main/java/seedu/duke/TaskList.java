package seedu.duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * TaskList class that contains the tasks and the actions to do on them.
 * It extends the Task class.
 */
public class TaskList {
    private static final String FANCY_LINE = "    -------------------------------------------\n";
    private static final String FORMAT_ISSUE = "Incorrect format";
    protected ArrayList<Task> list;

    /**
     * Constructor for TaskList class that takes an ArrayList of tasks.
     *
     * @param list ArrayList of tasks containing the tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Default constructor for TaskList class.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Marks a task as done.
     *
     * @param inputArgs input arguments.
     * @return output message
     * @throws DukeException if the task index is invalid.
     */
    public String mark(String[] inputArgs) throws DukeException {
        int index = Integer.parseInt(inputArgs[1]) - 1;
        try {
            list.get(index).isDone = true;
        } catch (IndexOutOfBoundsException e) {
            return "Invalid index";
        }

        String output = FANCY_LINE + "Nice! I've marked this task as done:\n";
        output += "    " + "[" + list.get(index).getStatusIcon() + "] ";
        output += list.get(index).description + "\n" + FANCY_LINE;

        return output;
    }

    /**
     * Marks a task as not done.
     *
     * @param inputArgs input arguments.
     * @return output message
     */
    public String unmark(String[] inputArgs) {
        int index = Integer.parseInt(inputArgs[1]) - 1;
        try {
            list.get(index).isDone = false;
        } catch (IndexOutOfBoundsException e) {
            return "Invalid index";
        }

        String output = FANCY_LINE + "Nice! I've marked this task as not done yet:\n";
        output += "    " + "[" + list.get(index).getStatusIcon() + "] ";
        output += list.get(index).description + "\n" + FANCY_LINE;
        return output;
    }

    /**
     * Displays the list of tasks.
     */
    public String showList() {
        StringBuilder reply = new StringBuilder();
        System.out.println(FANCY_LINE);
        for (int i = 0; i < list.size(); i++) {
            reply.append("    ").append(i + 1).append(".").append(list.get(i).toString()).append("\n");
            System.out.println("    " + (i + 1) + "." + list.get(i).toString());
        }
        System.out.println(FANCY_LINE);
        return reply.toString();
    }

    /**
     * Adds a ToDo task to the list.
     *
     * @param echoSplit an array of String containing the words of the command given.
     * @return output message
     */
    public String addToDo(String[] echoSplit) {
        StringBuilder task = new StringBuilder();
        assert echoSplit.length > 0 : "task length should not be less than 0";
        for (int i = 1; i < echoSplit.length; i++) {
            task.append(echoSplit[i]).append(" ");
        }
        if(task.toString().trim().equals("")) {
            return FORMAT_ISSUE;
        }
        list.add(new ToDo(task.toString().trim()));
        String output = FANCY_LINE + "added: " + task + "\n" + FANCY_LINE;
        return output;
    }

    /**
     * Adds an Event task to the list.
     *
     * @param echoSplit an array of String containing the words of the command given.
     * @param print an int that specifies whether to print the action or not.
     * @return output message
     */
    public String addEvent(String[] echoSplit, int print) {
        StringBuilder task = new StringBuilder();
        int fromIndex = 0;
        int toIndex = 0;
        String fromDate = "";
        String toDate = "";
        assert echoSplit.length > 0 : "task length should not be less than 0";

        for (int i = 1; i < echoSplit.length; i++) {
            boolean isFromWord = echoSplit[i].equals("/from") || echoSplit[i].equals("from:");
            boolean isToWord = echoSplit[i].equals("/to") || echoSplit[i].equals("to:");

            if (isFromWord) {
                fromIndex = i;
            }

            if (isToWord) {
                toIndex = i;

                for (int j = fromIndex + 1; j < toIndex; j++) {
                    fromDate += j == toIndex - 1 ? echoSplit[j] : echoSplit[j] + " ";
                }
                for (int j = toIndex + 1; j < echoSplit.length; j++) {
                    toDate += j == echoSplit.length - 1 ? echoSplit[j] : echoSplit[j] + " ";
                }
                for (int j = 1; j < fromIndex; j++) {
                    task.append(echoSplit[j]).append(" ");
                }
                break;
            }
        }
        String output;
        if(task.toString().trim().equals("")) {
            return FORMAT_ISSUE;
        }
        try {
            list.add(new Event(task.toString(), fromDate, toDate));
            output = FANCY_LINE + "added: " + task + "\n" + FANCY_LINE;
        } catch (DateTimeParseException e) {
            return FORMAT_ISSUE;
        }


        if (print == 0) {
            return output;
        } else {
            return "";
        }
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param echoSplit an array of String containing the words of the command given.
     * @param print an int that specifies whether to print the action or not.
     * @return output message
     */
    public String addDeadline(String[] echoSplit, int print) {
        String task = "";
        String date = "";

        for (int i = 1; i < echoSplit.length; i++) {
            boolean isByWord = echoSplit[i].equals("/by") || echoSplit[i].equals("by:");
            if (isByWord) {
                for (int j = 1; j < i; j++) {
                    task += j == i - 1 ? echoSplit[j] : echoSplit[j] + " ";
                }
                for (int j = i + 1; j < echoSplit.length; j++) {
                    date += j == echoSplit.length - 1 ? echoSplit[j] : echoSplit[j] + " ";
                }
                String output;
                if(task.equals("")) {
                    return FORMAT_ISSUE;
                }
                try {
                    list.add(new Deadline(task, date));
                    output = echoSplit[i + 1] + "\n" + FANCY_LINE + "added: " + task + "\n " + FANCY_LINE;
                } catch (DateTimeParseException e) {
                    return FORMAT_ISSUE;
                }

                if (print == 0) {
                    return output;
                } else {
                    return "";
                }
            }
        }
        return FORMAT_ISSUE;
    }

    /**
     * Deletes a task from the list.
     * Prints the task that was deleted.
     *
     * @param inputArgs input arguments.
     * @return output message
     */
    public String delete(String[] inputArgs) {
        int index = Integer.parseInt(inputArgs[1]) - 1;
        String item;
        try {
            item = list.get(index).toString();
            list.remove(index);
        } catch (IndexOutOfBoundsException e) {
            return "Invalid index";
        }
        String output = FANCY_LINE + "removed: " + item + "\n" + FANCY_LINE;
        return output;
    }

    /**
     * Searches for tasks that contain the given keyword.
     *
     * @param echoSplit an array of String containing the words of the command given.
     * @return output message
     */
    public String find(String[] echoSplit) {
        System.out.println(FANCY_LINE);
        String keyword = echoSplit[1];
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            String description = list.get(i).description;
            String[] taskSplit = description.split(" ");
            for (String word : taskSplit) {
                if (keyword.equals(word)) {
                    String taskString = "    " + (i + 1) + "." + list.get(i).toString();
                    result.append(taskString).append("\n");
                    System.out.println(taskString);
                }
            }
        }
        System.out.println(FANCY_LINE);
        return result.length() > 0 ? result.toString() : "not found";
    }
}
