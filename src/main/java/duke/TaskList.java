package duke;

import java.util.ArrayList;

/**
 * The TaskList class contains the task list.
 */
public class TaskList {
    private ArrayList<Task> lists = new ArrayList<>();

    /**
     * Generates a collection of tasks.
     *
     * @param inputs The user/file input.
     */
    public TaskList(ArrayList<String> inputs) {
        for (String item : inputs) {
            lists.add(Parser.parseFile(item));
        }
    }

    /**
     * Creates an empty task list.
     */
    public TaskList() {

    }

    /**
     * Returns the size of the list.
     *
     * @return The size of the list.
     */
    public int size() {
        return lists.size();
    }

    /**
     * Gets a specific task from the task list.
     *
     * @param index The task index.
     * @return The task of the corresponding index.
     */
    public Task get(int index) {
        return lists.get(index);
    }

    /**
     * Displays all the tasks in the list.
     */
    public String printList() {
        String allItems = "Here are the tasks in your list: \n";
        if (lists.size() == 0) {
            return "No tasks in your list.";
        } else {
            for (int i = 0; i < lists.size(); i++) {
                allItems = allItems + (i + 1) + ". " + lists.get(i) + "\n";
            }
            return allItems;
        }
    }

    /**
     * Marks a specific task as done.
     *
     * @param index The task to be selected.
     * @throws TaskNotExistException If the task does not exist in the list.
     */
    public String mark(int index) throws TaskNotExistException {
        if (index > lists.size() || index < 1) {
            throw new TaskNotExistException();
        }
        lists.get(index - 1).setIsDone();
        return "Nice! I've marked this task as done: \n" + lists.get(index - 1);
    }

    /**
     * Mark a specific task as not done.
     *
     * @param index The task to be selected.
     * @throws TaskNotExistException If the task does not exist in the list.
     */
    public String unmark(int index) throws TaskNotExistException {
        if (index > lists.size() || index < 1) {
            throw new TaskNotExistException();
        }
        lists.get(index - 1).revertIsDone();
        return "Ok! I've marked this task as not done: \n" + lists.get(index - 1);
    }

    /**
     * Creates a todo task.
     *
     * @param desc The description of a todo task.
     */
    public String todo(String desc) {
        Todo todo = new Todo(desc);
        lists.add(todo);
        return "Got it. I've added this task:\n" + todo + "\n" + "Now you have "
                + lists.size() + " tasks in the list.";
    }

    /**
     * Creates a deadline task.
     *
     * @param desc The description of a deadline task.
     * @param time The deadline of a deadline task.
     */
    public String deadline(String desc, TimeConvertor time) {
        Deadline deadline = new Deadline(desc, time);
        lists.add(deadline);
        return "Got it. I've added this task:\n" + deadline + "\n" + "Now you have "
                + lists.size() + " tasks in the list.";
    }

    /**
     * Creates an event task.
     *
     * @param desc The description of an event task.
     * @param from The start time of an event task.
     * @param to The end time of  a task.
     */
    public String event(String desc, TimeConvertor from, TimeConvertor to) {
        Event event = new Event(desc, from, to);
        lists.add(event);
        return "Got it. I've added this task:\n" + event + "\n" + "Now you have "
                + lists.size() + " tasks in the list.";
    }

    /**
     * Deletes the selected task.
     *
     * @param index The task to be selected.
     * @throws TaskNotExistException The selected task does not exist in the list.
     */
    public String delete(int index) throws TaskNotExistException {
        if (index > lists.size() || index < 1) {
            throw new TaskNotExistException();
        }
        return "Got it. I've remove this task:\n" + lists.remove(index - 1)
                + "\n" + "Now you have " + lists.size() + " tasks in the list.";
    }

    /**
     * Returns tasks with specific key word.
     *
     * @param searchKey Search key for finding the task.
     */
    public String find(String searchKey) {
        boolean isFind = false;
        String allFind = "";

        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getTaskDes().contains(searchKey.trim())) {
                isFind = true;
                allFind = allFind + (i + 1) + ". " + lists.get(i) + "\n";
            }
        }
        if (!isFind) {
            return "No result found.";
        }
        return allFind;
    }

    /**
     * Checks if there is deadline on a specific date.
     * Needs check keyword.
     * Format: check deadline /2019-10-15.
     *
     * @param checkDeadline The date.
     */
    public String check(String checkDeadline) {
        boolean ifDeadlineExist = false;
        String allCheck = "";

        for (int i = 0; i < lists.size(); i++) {
            Task currT = lists.get(i);
            if (currT instanceof Deadline) {
                Deadline dTask = (Deadline) currT;
                if (dTask.getDeadline().equals(checkDeadline)) {
                    ifDeadlineExist = true;
                    allCheck = allCheck + (i + 1) + "." + dTask;
                }
            }
        }
        if (!ifDeadlineExist) {
            return "No deadline found on " + checkDeadline;
        }
        return allCheck;
    }
}

