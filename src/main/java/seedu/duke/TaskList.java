package seedu.duke;

import java.text.NumberFormat;
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
        int index;
        try {
            index = Integer.parseInt(inputArgs[1]) - 1;
            list.get(index).isDone = true;
        } catch (IndexOutOfBoundsException e) {
            return "Invalid index";
        } catch (NumberFormatException a) {
            return "Invalid format; Please type a number";
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
    public String addEvent(String[] echoSplit) {
        String task;
        Event event;
        try {
            int fromIndex = findIndex(echoSplit, "/from");
            int toIndex = findIndex(echoSplit, "/to");

            task = extractData(echoSplit, 1, fromIndex) + " ";
            String fromDate = extractData(echoSplit, fromIndex + 1, toIndex);
            String toDate = extractData(echoSplit, toIndex + 1, echoSplit.length);

            event = new Event(task, fromDate, toDate);
        } catch (DateTimeParseException e) {
            return FORMAT_ISSUE;
        } catch(IndexOutOfBoundsException a) {
            return FORMAT_ISSUE;
        }
        return deHelper(event, task);
    }

    public String addDeadline(String[] echoSplit) {


        String task;
        Deadline deadline;
        try {
            int byIndex = findIndex(echoSplit, "/by");

            task = extractData(echoSplit, 1, byIndex);
            String date = extractData(echoSplit, byIndex + 1, echoSplit.length);

            deadline = new Deadline(task, date);
        } catch (DateTimeParseException e) {
            return FORMAT_ISSUE;
        } catch(IndexOutOfBoundsException a) {
            return FORMAT_ISSUE;
        }
        return deHelper(deadline, task);
    }

    private String extractData(String[] echoSplit, int start, int end) {
        StringBuilder data = new StringBuilder();

        for (int i = start; i < end; i++) {
            if (i != start) data.append(" ");
            data.append(echoSplit[i]);
        }

        return data.toString();
    }

    private int findIndex(String[] echoSplit, String... targets) {
        for (int i = 0; i < echoSplit.length; i++) {
            for (String target : targets) {
                if (echoSplit[i].equals(target)) return i;
            }
        }

        return -1;
    }

    private String deHelper(Task task, String taskDescription) {
        String output;
        if (taskDescription.trim().equals("")) {
            return FORMAT_ISSUE;
        }
        list.add(task);
        output = FANCY_LINE + "added: " + taskDescription + "\n" + FANCY_LINE;

        return output;
    }

    /**
     * Deletes a task from the list.
     * Prints the task that was deleted.
     *
     * @param inputArgs input arguments.
     * @return output message
     */
    public String delete(String[] inputArgs) {
        int index;
        String item;
        try {
            index = Integer.parseInt(inputArgs[1]) - 1;
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
        String keyword;
        StringBuilder result;
        try {
            System.out.println(FANCY_LINE);
            keyword = echoSplit[1];
            result = new StringBuilder();
        } catch (ArrayIndexOutOfBoundsException w) {
            return "invalid format";
        }

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
