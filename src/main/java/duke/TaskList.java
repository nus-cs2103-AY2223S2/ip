package duke;

import java.util.ArrayList;

/**
 * A task list that handles operations from the user to manage their tasks and interact with the storage
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Creates a new task list object to handle operations from the user
     *
     * @param taskList the initialised task list from the storage txt file
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Takes in a user text input, processes it to delete a record, and returns the response message
     *
     * @param textInput the input message from the user
     * @return the response message to be printed
     */
    public String delete(String textInput) {
        int i = Integer.parseInt(textInput.substring(7));
        Task t = taskList.get(i - 1);
        taskList.remove(i - 1);
        String removedText = String.format("Got it. I've removed this task: %s\n", t.toString());
        String listSize = String.format("Now you have %d tasks in the list", taskList.size());
        return removedText + listSize;
    }

    /**
     * Takes in a user text input, processes it to mark a record as completed, and returns the response message
     *
     * @param textInput the input message from the user
     * @return the response message to be printed
     */
    public String mark(String textInput) {
        int i = Integer.parseInt(textInput.substring(5));
        Task currTask = taskList.get(i - 1);
        currTask.markDone();
        return "Nice! I've marked this task as done\n" + currTask;
    }

    /**
     * Takes in a user text input, processes it to mark a record as uncompleted, and returns the response message
     *
     * @param textInput the input message from the user
     * @return the response message to be printed
     */
    public String unmark(String textInput) {
        int i = Integer.parseInt(textInput.substring(7));
        Task currTask = taskList.get(i - 1);
        currTask.markUndone();
        return "OK, I've marked this task as not done yet:\n" + currTask;
    }

    /**
     * Takes in a user text input, processes it to find matching records, and returns the response message
     *
     * @param textInput the input message from the user
     * @return the response message to be printed
     * @throws DukeException if no find keyword is given
     */
    public String find(String textInput) throws DukeException {
        String[] parts = textInput.split(" ", 2);
        if (parts.length == 1 || parts[1].equals("")) {
            throw new DukeException("☹ OOPS!!! The description of find command cannot be empty.");
        }
        String keyword = "(.*)" + textInput.substring(5) + "(.*)";
        String result = "Here are the matching tasks in your list:\n";
        boolean found = false;
        int index = 1;
        for (int i = 0; i < taskList.size(); i++) {
            String description = taskList.get(i).toString();
            if (description.matches(keyword)) {
                found = true;
                String output = String.format("%d. %s", index, description + "\n");
                result += output;
                index++;
            }
        }
        return found ? result.trim() : "Sorry, there are no matching tasks :-(";
    }

    /**
     * Takes in a user text input, processes it add a to-do task, and returns the response message
     *
     * @param textInput the input message from the user
     * @return the response message to be printed
     * @throws DukeException if the text input is invalid
     */
    public String todo(String textInput) throws DukeException {
        String[] parts = textInput.split(" ", 2);
        if (parts.length == 1 || parts[1].equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task t = new Task.Todo(textInput.substring(5));
        taskList.add(t);
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list", t, taskList.size());
    }

    /**
     * Takes in a user text input, processes it add a deadline task, and returns the response message
     *
     * @param textInput the input message from the user
     * @return the response message to be printed
     */
    public String deadline(String textInput) {
        String[] parts = textInput.split("/");
        Task t = new Task.Deadline(parts[0].substring(9), parts[1].substring(3));
        taskList.add(t);
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list", t, taskList.size());
    }

    /**
     * Takes in a user text input, processes it add an event task, and returns the response message
     *
     * @param textInput the input message from the user
     * @return the response message to be printed
     */
    public String event(String textInput) {
        String[] parts = textInput.split("/");
        Task t = new Task.Event(parts[0].substring(6), parts[1].substring(5), parts[2].substring(3));
        taskList.add(t);
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list", t, taskList.size());
    }

    /**
     * Processes the current TaskList object into a string formatted for txt storage
     *
     * @return the referenced TaskList object as a string formatted for txt storage
     */
    public String toWrite() {
        int length = this.taskList.size();
        String result = "";
        for (int i = 0; i < length; i++) {
            String item = taskList.get(i).toData();
            result += item + "\n";
        }
        return result.trim();
    }

    /**
     * Returns the String representation of the referenced TaskList
     *
     * @return the String representation of the TaskList
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            String description = taskList.get(i).toString();
            String output = String.format("%d. %s", i + 1, description + "\n");
            result += output;
        }
        return result.trim();
    }
}
