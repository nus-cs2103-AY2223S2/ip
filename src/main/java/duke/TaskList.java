package duke;

import java.util.ArrayList;
import java.util.PriorityQueue;

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
        if ((userInput.split("\\s+")).length == 1) {
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
     * @throws MissingNumberException If user not specific which task to mark.
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
     * Marks a specific task as not done.
     *
     * @param userInput User input.
     * @return Return unmarked message.
     * @throws TaskNotExistException If the task does not exist in the list.
     * @throws MissingNumberException If user not specific which task to unmark.
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
     * @throws MissingDescriptionException If to do task is missing a description.
     */
    public String todo(String userInput) throws MissingDescriptionException {
        if ((userInput.split("\\s+")).length == 1) {
            throw new MissingDescriptionException("todo");
        }

        String desc = userInput.substring(userInput.indexOf(" ")).trim();

        Todo todo = new Todo(desc);
        lists.add(todo);
        return "Got it. I've added this task:\n" + todo + "\n" + "Now you have "
                + lists.size() + " tasks in the list.";
    }

    private boolean isDeadlineValid(String ddDate, String ddDescription) {
        boolean isMissingDate = (ddDate.trim()).length() == 0;
        boolean isMissingDescription = (ddDescription.trim()).length() == 0;
        boolean isInvalidFormat = isMissingDate || isMissingDescription;

        return isInvalidFormat;
    }

    /**
     * Creates a deadline task.
     *
     * @param userInput User input.
     * @return Return deadline message.
     * @throws MissingDescriptionException If deadline task is missing a description.
     */
    public String deadline(String userInput) throws MissingDescriptionException {
        String ddDate = "";
        String ddDescription = "";
        int i = 1;
        String[] input = userInput.split("\\s+");

        while (i < input.length) {
            if (input[i].equals("/by")) {
                ddDate = input[i + 1] + " " + input[i + 2];
                i += 3;
            } else {
                ddDescription += input[i] + " ";
                i += 1;
            }
        }

        if (isDeadlineValid(ddDate, ddDescription)) {
            throw new MissingDescriptionException("deadline");
        }

        Deadline deadline = new Deadline(ddDescription, new TimeConvertor(ddDate));
        lists.add(deadline);
        return "Got it. I've added this task:\n" + deadline + "\n" + "Now you have "
                + lists.size() + " tasks in the list.";
    }

    private boolean isEventValid(String eventFrom, String eventTo, String eventDescription) {
        boolean isMissingFrom = (eventFrom.trim()).length() == 0;
        boolean isMissingTo = (eventTo.trim()).length() == 0;
        boolean isMissingDescription = (eventDescription.trim()).length() == 0;
        boolean isInvalidFormat = isMissingFrom || isMissingTo || isMissingDescription;

        return isInvalidFormat;
    }

    /**
     * Creates an event task.
     *
     * @param userInput User input.
     * @return Return event message.
     * @throws MissingDescriptionException If event task is missing a description.
     */
    public String event(String userInput) throws MissingDescriptionException {
        String eventDescription = "";
        String eventFrom = "";
        String eventTo = "";
        int i = 1;
        String[] input = userInput.split("\\s+");

        while (i < input.length) {
            if (input[i].equals("/from")) {
                eventFrom = input[i + 1] + " " + input[i + 2];
                i += 3;
            } else if (input[i].equals("/to")) {
                eventTo = input[i + 1] + " " + input[i + 2];
                i += 3;
            } else {
                eventDescription += input[i] + " ";
                i += 1;
            }
        }

        if (isEventValid(eventFrom, eventTo, eventDescription)) {
            throw new MissingDescriptionException("event");
        }

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
     * @throws TaskNotExistException If the selected task does not exist in the list.
     * @throws MissingNumberException If user not specific which task to delete.
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
     * @throws MissingDescriptionException If user not specific what to find.
     */
    public String find(String userInput) throws MissingDescriptionException {
        if ((userInput.split("\\s+")).length == 1) {
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
     * @throws CheckNotFindException If user not specific which task to check.
     */
    public String check(String userInput) throws CheckNotFindException {
        if (!userInput.contains("/")) {
            throw new CheckNotFindException();
        }

        String checkDeadline = userInput.split("/")[1];

        boolean isDeadlineNotExist = true;
        String allCheck = "";

        for (int i = 0; i < lists.size(); i++) {
            Task currT = lists.get(i);

            if (!(currT instanceof Deadline)) {
                continue;
            }

            Deadline dTask = (Deadline) currT;
            if (dTask.getDeadline().equals(checkDeadline)) {
                isDeadlineNotExist = false;
                allCheck = allCheck + (i + 1) + "." + dTask + "\n";
            }
        }

        if (isDeadlineNotExist) {
            return "No deadline found";
        }

        return allCheck;
    }


    /**
     * Displays the sorted sequence of deadline or event.
     *
     * @param userInput What user want to sort.
     * @return The sorted list of deadline or event based on the user's choice.
     * @throws NoSortTypeException If sort is not applied to deadline or event type.
     */
    public String sort(String userInput) throws NoSortTypeException {
        if ((userInput.split("\\s+")).length == 1) {
            throw new NoSortTypeException();
        }

        String type = userInput.split(" ")[1].trim();
        String result = "";
        PriorityQueue<Task> pqTasks = new PriorityQueue<>();

        for (Task items: lists) {
            if (type.equals("deadline") && items instanceof Deadline) {
                pqTasks.add(items);
            } else if (type.equals("event") && items instanceof Event) {
                pqTasks.add(items);
            }
        }

        while (!pqTasks.isEmpty()) {
            result += "• " + pqTasks.poll() + "\n";
        }

        return result.length() != 0 ? result : "No result return! Either you do not have deadline/event "
                + "task in your list currently or your sort is not valid. ";
    }
}

