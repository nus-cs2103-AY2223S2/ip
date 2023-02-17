package duke;

/**
 * UI object that displays the responses of Duke
 */
public class UI {
    /**
     * Generates welcome message
     * @return Welcome message
     */
    public String start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String out = "Hello from\n" + logo
                + "\n('v') :: Hiii! I'm duke, here to help you with your tasks!\n"
                + "•──────────────────♛─────────────────•";
        return out;
    }

    /**
     * Displays the tasks in the TaskList
     * @param list Current TaskList
     * @return String for list of tasks
     */
    public String showList(TaskList list) {
        String out = "('.') :: Here are the task(s) in your list:";
        for (int i = 1; i <= list.getSize(); i++) {
            out = out +"\n" + i + "." + list.getTask(i-1).toString();
        }
        return out;
    }

    /**
     * Displays the tasks that match the keyword
     * @param list List of tasks that match the keyword
     * @return String for list of found tasks
     */
    public String showFoundTasks(TaskList list) {
        String out = "('v') :: Here are the matching task(s) in your list:";
        for (int i = 1; i <= list.getSize(); i++) {
            out = out +"\n" + i + "." + list.getTask(i-1).toString();
        }
        return out;
    }

    /**
     * Marks a task
     * @param list Current TaskList
     * @param index Index of the task to be marked
     * @return Message for marking task
     */
    public String mark(TaskList list, int index) {
        list.getTask(index - 1).setDone();
        String out = "('v')! :: Nice! I've marked this task as done:\n  " + list.getTask(index - 1).toString();
        return out;
    }

    /**
     * Unmarks a task
     * @param list Current TaskList
     * @param index Index of the task to be unmarked
     * @return Message for unmarking task
     */
    public String unmark(TaskList list, int index) {
        list.getTask(index - 1).setUndone();
        String out = "('v')! :: OK, I've marked this task as not done yet:\n  " + list.getTask(index - 1).toString();
        return out;
    }

    /**
     * Adds a Todo task into the list
     * @param list Current TaskList
     * @param name Description of the Todo task
     * @return Message for adding todo task
     */
    public String addTodo(TaskList list, String name) {
        list.addTask(new ToDo(name));
        String out = "('v')! :: Got it. I've added this task:\n";
        out += "  " + list.getTask(list.getSize() - 1).toString();
        out += "\n('.') :: Now you have " + list.getSize() + " task(s) in the list.";
        return out;
    }

    /**
     * Adds a deadline task into the list
     * @param list Current TaskList
     * @param name Description of the deadline task
     * @param deadline Deadline of the deadline task
     * @return Message for adding deadline task
     */
    public String addDeadline(TaskList list, String name, String deadline) {
        list.addTask(new Deadline(name, deadline));
        String out = "('v')! :: Got it. I've added this task:\n";
        out += "  " + list.getTask(list.getSize() - 1).toString();
        out += "\n('.') :: Now you have " + list.getSize() + " task(s) in the list.";
        return out;
    }

    /**
     * Adds an event task into the list
     * @param list Current TaskList
     * @param name Description of the event task
     * @param start Start time/date of the event task
     * @param end End time/date of the event task
     * @return Message for adding event task
     */
    public String addEvent(TaskList list, String name, String start, String end) {
        list.addTask(new Event(name, start, end));
        String out = "('v')! :: Got it. I've added this task:\n";
        out += "  " + list.getTask(list.getSize() - 1).toString();
        out += "\n('.') :: Now you have " + list.getSize() + " task(s) in the list.";
        return out;
    }

    /**
     * Removes the task at the given index of the list
     * @param list Description of the event task
     * @param index Index of the task to be removed
     * @return Message for removing task
     */
    public String removeTask(TaskList list, int index) {
        String out = "('o') :: Noted. I've removed this task :\n";
        out += "  " + list.removeTask(index - 1).toString();
        out += "\n('.') :: Now you have " + list.getSize() + " task(s) in the list.";
        return out;
    }

    /**
     * Show exit message
     * @return the exit message
     */
    public String showExit() {
        return "('v')!! :: Buhbyeee, hope to see you again soon!";
    }

    /**
     * Show exception message
     * @param e DukeException
     * @return Exception message
     */
    public String showException(DukeException e) {
        return e.toString();
    }

    // Extension: C-Help
    /**
     * Displays help menu
     * @return Help menu
     */
    public String getHelp() {
        String out = "Available features: \n";
        out += "'retrieve data' -> retrieve data from where you left off last\n";
        out += "'list' -> list out current tasks\n";
        out += "'todo [description]' -> add a todo task to the list\n";
        out += "'deadline [description] /by [deadline]' -> add a deadline task to the list\n";
        out += "'event [description] /from [start] /to [end]' -> add an event task to the list\n";
        out += "'mark [task number]' -> marks the task as done\n";
        out += "'unmark [task number]' -> marks the task as not done\n";
        out += "'delete [task number]' -> removes task from the list\n";
        out += "'find [keyword]' -> lists all the tasks containing the keyword\n";
        out += "'bye' -> exits the program";
        return out;
    }
}
