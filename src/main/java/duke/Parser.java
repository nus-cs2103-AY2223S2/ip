package duke;

/**
 * Class representing a Parser to parse user input.
 */
public class Parser {
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String LIST = "list";
    private static final String DELETE = "delete";
    private static final String FIND = "find";
    private static final String SORT = "sort";

    private static final int INDEX_TODO_DESCRIPTION = TODO.length();
    private static final int INDEX_DEADLINE_DESCRIPTION = DEADLINE.length();
    private static final int INDEX_EVENT_DESCRIPTION = EVENT.length();
    private static final int INDEX_DEADLINE_BY = "by".length();
    private static final int INDEX_EVENT_FROM = "from".length();
    private static final int INDEX_EVENT_TO = "to".length();
    private static final int INDEX_MARK = MARK.length();
    private static final int INDEX_UNMARK = UNMARK.length();
    private static final int INDEX_DELETE = DELETE.length();
    private static final int INDEX_FIND = FIND.length();

    private TaskList taskList;
    private Ui ui;

    /**
     * Parameterized constructor to create a Parser
     * @param taskList the TaskList which has to be parsed
     * @param ui the Ui of the agent on which messages have to be displayed
     */
    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Returns the current list of tasks
     * @return the current list of tasks
     */
    private String showList() {
        return ui.getFormattedTaskList(taskList);
    }

    /**
     * Marks the Task at a specified index as done
     * @param index the index of the Task to be marked as done
     * @return the Ui response on marking the Task as done
     */
    private String mark(int index) {
        taskList.mark(index);
        Task task = taskList.get(index);
        return ui.getMarkMessage(task, index);
    }

    /**
     * Marks the Task at a specified index as undone
     * @param index the index of the Task to be marked as undone
     * @return the Ui response on marking the Task as undone
     */
    private String unmark(int index) {
        taskList.unmark(index);
        Task task = taskList.get(index);
        return ui.getUnmarkMessage(task, index);
    }

    /**
     * Deletes the Task at a specified index
     * @param index the index of the Task to be deleted
     * @return the Ui response on deleting the Task
     */
    private String delete(int index) {
        Task deletedTask = taskList.delete(index);
        return ui.getDeleteMessage(deletedTask, taskList);
    }

    /**
     * Adds a Todo to the TaskList
     * @param task the String representing the Todo to be added
     * @throws DukeException if the description of the Todo is empty
     */
    private void addTodo(String task) throws DukeException {
        String description = task.substring(INDEX_TODO_DESCRIPTION).trim();
        if (description.trim().equals("")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        assert (!description.equals("")) : "Description cannot be empty";
        taskList.add(new Todo(description));
    }

    /**
     * Adds a Deadline to the TaskList
     * @param task the String representing the Deadline to be added
     */
    private void addDeadline(String task) {
        String[] arr = task.split("/");
        String description = arr[0].substring(INDEX_DEADLINE_DESCRIPTION).trim();
        String by = arr[1].substring(INDEX_DEADLINE_BY).trim();

        assert (!description.equals("")) : "Description cannot be empty";
        assert (!by.equals("")) : "By cannot be empty";

        taskList.add(new Deadline(description, by));
    }

    /**
     * Adds an Event to the TaskList
     * @param task the String representing the Event to be added
     */
    private void addEvent(String task) {
        assert task != null;
        String[] arr = task.split("/");
        String description = arr[0].substring(INDEX_EVENT_DESCRIPTION).trim();
        String from = arr[1].substring(INDEX_EVENT_FROM).trim();
        String to = arr[2].substring(INDEX_EVENT_TO).trim();
        assert (!description.equals("")) : "Description cannot be empty";
        assert (!from.equals("")) : "From cannot be empty";
        assert (!to.equals("")) : "To cannot be empty";

        taskList.add(new Event(description, from, to));
    }

    /**
     * Finds an event with a given keyword in the TaskList
     * @param task the String representing the Task with the keyword to be found
     * @return the Ui response containing the matching list of tasks in the list
     */
    private String find(String task) {
        String keyword = task.substring(INDEX_FIND).trim();
        TaskList matchingTasks = taskList.find(keyword);
        return ui.getFormattedTaskList(matchingTasks, true);
    }

    /**
     * Adds a Task to the TaskList
     * @param input the String containing a single line of user input
     * @return the Ui response on adding a Task
     * @throws DukeException if an invalid command is entered
     */
    private String add(String input) throws DukeException {
        if (input.startsWith(TODO)) {
            addTodo(input);
        } else if (input.startsWith(DEADLINE)) {
            addDeadline(input);
        } else if (input.startsWith(EVENT)) {
            addEvent(input);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        int len = taskList.size();
        Task t = taskList.get(len - 1);
        return ui.getAddMessage(t, taskList);
    }

    private String sortDeadlines() {
        return ui.getFormattedTaskList(taskList.sortDeadlines(), true);
    }

    /**
     * Parses a single line of user input
     * @param input the String containing a single line of user input
     * @return the Ui response to the input
     * @throws DukeException if an invalid command is entered
     */
    public String parse(String input) throws DukeException {
        if (input.equals(LIST)) {
            return showList();
        } else if (input.startsWith(MARK)) {
            int index = Integer.parseInt(input.substring(INDEX_MARK).trim()) - 1;
            return mark(index);
        } else if (input.startsWith(UNMARK)) {
            int index = Integer.parseInt(input.substring(INDEX_UNMARK).trim()) - 1;
            return unmark(index);
        } else if (input.startsWith(DELETE)) {
            int index = Integer.parseInt(input.substring(INDEX_DELETE).trim()) - 1;
            return delete(index);
        } else if (input.startsWith(FIND)) {
            return find(input);
        } else if (input.startsWith(SORT)) {
            return sortDeadlines();
        } else {
            return add(input);
        }
    }
}
