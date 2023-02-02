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
     * Exits from the application.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon! ^_^");
        System.exit(0);
    }

    /**
     * Displays all the tasks in the list.
     */
    public void printList() {
        if (lists.size() == 0) {
            System.out.println("No tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < lists.size(); i++) {
                System.out.println(i + 1 + "." + lists.get(i));
            }
        }
    }

    /**
     * Marks a specific task as done.
     *
     * @param index The task to be selected.
     * @throws TaskNotExistException If the task does not exist in the list.
     */
    public void mark(int index) throws TaskNotExistException {
        if (index > lists.size() || index < 1) {
            throw new TaskNotExistException();
        }
        lists.get(index - 1).setIsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(lists.get(index - 1));
    }

    /**
     * Mark a specific task as not done.
     *
     * @param index The task to be selected.
     * @throws TaskNotExistException If the task does not exist in the list.
     */
    public void unmark(int index) throws TaskNotExistException {
        if (index > lists.size() || index < 1) {
            throw new TaskNotExistException();
        }
        lists.get(index - 1).revertIsDone();
        System.out.println("Nice! I've marked this task as not done:");
        System.out.println(lists.get(index - 1));
    }

    /**
     * Creates a todo task.
     *
     * @param desc The description of a todo task.
     */
    public void todo(String desc) {
        Todo todo = new Todo(desc);
        lists.add(todo);
        System.out.println("Got it. I've added this task:\n" + todo);
        System.out.println("Now you have " + lists.size() + " tasks in the list.");
    }

    /**
     * Creates a deadline task.
     *
     * @param desc The description of a deadline task.
     * @param time The deadline of a deadline task.
     */
    public void deadline(String desc, TimeConvertor time) {
        Deadline deadline = new Deadline(desc, time);
        lists.add(deadline);
        System.out.println("Got it. I've added this task:\n" + deadline);
        System.out.println("Now you have " + lists.size() + " tasks in the list.");
    }

    /**
     * Creates an event task.
     *
     * @param desc The description of an event task.
     * @param from The start time of an event task.
     * @param to The end time of  a task.
     */
    public void event(String desc, TimeConvertor from, TimeConvertor to) {
        Event event = new Event(desc, from, to);
        lists.add(event);
        System.out.println("Got it. I've added this task:\n" + event);
        System.out.println("Now you have " + lists.size() + " tasks in the list.");
    }

    /**
     * Deletes the selected task.
     *
     * @param index The task to be selected.
     * @throws TaskNotExistException The selected task does not exist in the list.
     */
    public void delete(int index) throws TaskNotExistException {
        if (index > lists.size() || index < 1) {
            throw new TaskNotExistException();
        }
        System.out.println("Got it. I've remove this task:\n" + lists.remove(index - 1));
        System.out.println("Now you have " + lists.size() + " tasks in the list.");
    }

    /**
     * Returns tasks with specific key word.
     *
     * @param searchKey Search key for finding the task.
     */
    public void find(String searchKey) {
        boolean isFind = false;
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getTaskDes().contains(searchKey.trim())) {
                System.out.println((i + 1) + ". " + lists.get(i));
                isFind = true;
            }
        }
        if (!isFind) {
            System.out.println("No result found.");
        }
    }

    /**
     * Checks if there is deadline on a specific date.
     * Needs check keyword.
     * Format: check deadline /2019-10-15.
     *
     * @param checkDeadline The date.
     */
    public void check(String checkDeadline) {
        boolean ifDeadlineExist = false;
        for (int i = 0; i < lists.size(); i++) {
            Task currT = lists.get(i);
            if (currT instanceof Deadline) {
                Deadline dTask = (Deadline) currT;
                if (dTask.getDeadline().equals(checkDeadline)) {
                    ifDeadlineExist = true;
                    System.out.println((i + 1) + "." + dTask);
                }
            }
        }
        if (!ifDeadlineExist) {
            System.out.println("No deadline found on " + checkDeadline);
        }
    }
}

