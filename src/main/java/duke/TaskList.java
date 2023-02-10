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
        //For the program to initialize, create an empty task list.
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
        }

        for (int i = 0; i < lists.size(); i++) {
            allItems = allItems + (i + 1) + ". " + lists.get(i) + "\n";
        }

        return allItems;
    }

    private boolean isIndexExist(int index) {
        boolean isLargerThanSize = index > lists.size();
        boolean isIndexSmaller = index < 1;
        boolean isInvalidIndex = isLargerThanSize || isIndexSmaller;
        return isInvalidIndex;
    }

    private int checkIndex(String userInput, String type) throws MissingNumberException, TaskNotExistException {
        if (!userInput.contains(" ")) {
            throw new MissingNumberException(type);
        }

        int index = Integer.parseInt(userInput.split("\\s+")[1]);

        if (isIndexExist(index)) {
            throw new TaskNotExistException();
        }

        return index;
    }

    /**
     * Marks a specific task as done.
     *
     * @param userInput User input.
     * @return Return marked message.
     * @throws TaskNotExistException If the task does not exist in the list.
     * @throws MissingNumberException User not specific which task to mark.
     */
    public String mark(String userInput) throws TaskNotExistException, MissingNumberException {
        int index = checkIndex(userInput, "mark");
        assert index >= 1 : "User input index smaller than 1";
        assert index <= lists.size() : "User input index larger than the current list size";
        if (index > lists.size() || index < 1) {
            throw new TaskNotExistException();
        }
        lists.get(index - 1).setIsDone();
        return "Nice! I've marked this task as done: \n" + lists.get(index - 1);
    }

    /**
     * Mark a specific task as not done.
     *
     * @param userInput User input.
     * @return Return unmarked message.
     * @throws TaskNotExistException If the task does not exist in the list.
     * @throws MissingNumberException User not specific which task to unmark.
     */
    public String unmark(String userInput) throws TaskNotExistException, MissingNumberException {
        int index = checkIndex(userInput, "unmark");
        assert index >= 1 : "User input index smaller than 1";
        assert index <= lists.size() : "User input index larger than the current list size";
        if (index > lists.size() || index < 1) {
            throw new TaskNotExistException();
        }
        lists.get(index - 1).revertIsDone();
        return "Ok! I've marked this task as not done: \n" + lists.get(index - 1);
    }

    /**
     * Creates a to do task.
     *
     * @param userInput User input.
     * @return Return to do task message.
     * @throws MissingDescriptionException To do task is missing a description.
     */
    public String todo(String userInput) throws MissingDescriptionException {
        if (!userInput.contains(" ")) {
            throw new MissingDescriptionException("todo");
        }

        String desc = userInput.substring(userInput.indexOf(" ")).trim();

        Todo todo = new Todo(desc);
        lists.add(todo);
        return "Got it. I've added this task:\n" + todo + "\n" + "Now you have "
                + lists.size() + " tasks in the list.";
    }

    /**
     * Creates a deadline task.
     *
     * @param userInput User input.
     * @return Return deadline message.
     * @throws MissingDescriptionException Deadline task is missing a description.
     */
    public String deadline(String userInput) throws MissingDescriptionException {
        if (!userInput.contains(" ")) {
            throw new MissingDescriptionException("deadline");
        }
        if (!userInput.contains("/")) {
            throw new MissingDescriptionException("deadline");
        }
        String ddFull = userInput.substring(userInput.indexOf(" ")).trim();
        String ddDescription = ddFull.split("/")[0];
        String ddDate = ddFull.split("/")[1].substring(ddFull.split("/")[1]
                .indexOf(" ")).trim();

        Deadline deadline = new Deadline(ddDescription, new TimeConvertor(ddDate));
        lists.add(deadline);
        return "Got it. I've added this task:\n" + deadline + "\n" + "Now you have "
                + lists.size() + " tasks in the list.";
    }

    /**
     * Creates an event task.
     *
     * @param userInput User input.
     * @return Return event message.
     * @throws MissingDescriptionException Event task is missing a description.
     */
    public String event(String userInput) throws MissingDescriptionException {
        if (!userInput.contains(" ")) {
            throw new MissingDescriptionException("event");
        }
        if (!userInput.contains("/")) {
            throw new MissingDescriptionException("event");
        }
        String eventFull = userInput.substring(userInput.indexOf(" ")).trim();
        String eventDescription = eventFull.split("/")[0];
        String eventFrom = eventFull.split("/")[1]
                .substring(eventFull.split("/")[1].indexOf(" ")).trim();
        String eventTo = eventFull.split("/")[2].substring(eventFull
                .split("/")[2].indexOf(" ")).trim();

        Event event = new Event(eventDescription, new TimeConvertor(eventFrom), new TimeConvertor(eventTo));
        lists.add(event);
        return "Got it. I've added this task:\n" + event + "\n" + "Now you have "
                + lists.size() + " tasks in the list.";
    }

    /**
     * Deletes the selected task.
     *
     * @param userInput User input.
     * @return Return delete messages.
     * @throws TaskNotExistException The selected task does not exist in the list.
     * @throws MissingNumberException User not specific which task to delete.
     */
    public String delete(String userInput) throws TaskNotExistException, MissingNumberException {
        int index = checkIndex(userInput, "delete");
        assert index >= 1 : "User input index smaller than 1";
        assert index <= lists.size() : "User input index larger than the current list size";
        if (index > lists.size() || index < 1) {
            throw new TaskNotExistException();
        }
        return "Got it. I've remove this task:\n" + lists.remove(index - 1)
                + "\n" + "Now you have " + lists.size() + " tasks in the list.";
    }

    /**
     * Returns tasks with specific key word.
     *
     * @param userInput Search key for finding the tasks.
     * @return Return task is found or not.
     * @throws MissingNumberException User not specific which task to find.
     */
    public String find(String userInput) throws MissingDescriptionException {
        if (!userInput.contains(" ")) {
            throw new MissingDescriptionException("find");
        }

        String searchKey = userInput.substring(userInput.indexOf(" ") + 1);

        boolean isNotFind = true;
        String allFind = "";

        //The arrow head need to maintain here as there might be multiple items that matches.
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getTaskDes().contains(searchKey.trim())) {
                isNotFind = false;
                allFind = allFind + (i + 1) + ". " + lists.get(i) + "\n";
            }
        }

        if (isNotFind) {
            return "No result found.";
        }

        return allFind;
    }

    /**
     * Checks if there is deadline on a specific date.
     * Needs check keywords.
     * Format: check deadline /2019-10-15.
     *
     * @param userInput User input.
     * @return Return the items that fulfill the check condition.
     * @throws MissingDescriptionException User not specific which task to check.
     */
    public String check(String userInput) throws CheckNotFindException {
        if (!userInput.contains("/")) {
            throw new CheckNotFindException();
        }
        String checkDeadline = userInput.split("/")[1];

        boolean ifDeadlineNotExist = true;
        String allCheck = "";

        for (int i = 0; i < lists.size(); i++) {
            Task currT = lists.get(i);

            if (!(currT instanceof Deadline)) {
                continue;
            }

            Deadline dTask = (Deadline) currT;
            if (dTask.getDeadline().equals(checkDeadline)) {
                ifDeadlineNotExist = false;
                allCheck = allCheck + (i + 1) + "." + dTask + "\n";
            }
        }

        if (ifDeadlineNotExist) {
            return "No deadline found on " + checkDeadline;
        }

        return allCheck;
    }
}

