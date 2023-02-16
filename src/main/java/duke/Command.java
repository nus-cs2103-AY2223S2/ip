package duke;

import java.util.ArrayList;

/**
 * a class to manage all executable commands.
 */
public class Command {
    private final String INPUT;
    private final TaskList LIST;
    private final Ui UI;

    /**
     * constructs a command object.
     * @param input input entered by user.
     * @param list the TaskList object to be accessed.
     * @param ui the Ui object that returns the response.
     */
    public Command(String input, TaskList list, Ui ui) {
        this.INPUT = input;
        this.UI = ui;
        this.LIST = list;
    }

    /**
     * show the list of tasks.
     * @param list the TaskList object that stores our tasks.
     * @return a String containing the tasks.
     */
    public String listCommand(TaskList list) {
    return this.UI.showTaskList(list);
    }

    /**
     * mark a task as done.
     * @return a String showing which task is marked.
     */
    public String markCommand() {
        String str = INPUT.replace("mark ", "");
        int index = Integer.parseInt(str);
        index--;
        Task task = LIST.get(index);
        task.markAsDone();
        return UI.showMarked(task);
    }

    /**
     * un-mark a task.
     * @return a String showing which task is un-marked.
     */
    public String unmarkCommand() {
        String str = INPUT.replace("unmark ", "");
        int index = Integer.parseInt(str);
        index--;
        Task task = LIST.get(index);
        task.unmark();
        return UI.showUnmarked(task);
    }

    /**
     * create a toDo task.
     * @return a String showing the newly created task.
     * @throws EmptyDescriptionException if description of task is empty.
     */
    public String toDoCommand() throws EmptyDescriptionException {
        String str = "";
        if(INPUT.startsWith("todo")) {
            str = INPUT.replace("todo", "");
        } else if (INPUT.startsWith("t ")) {
            str = INPUT.substring(2, INPUT.length());
        }
        ToDo toDo = new ToDo(str);
        LIST.add(toDo);
        return UI.showAdd(toDo) + UI.showTaskSize(LIST.size());
    }

    /**
     * creates a deadline task.
     * @return a String showing the newly created task.
     * @throws EmptyDescriptionException if description of task is empty.
     */
    public String deadlineCommand() throws EmptyDescriptionException {
        String str = "";
        if (INPUT.startsWith("deadline")) {
            str = INPUT.replace("deadline", "");
        } else if (INPUT.startsWith("d ")) {
            str = INPUT.substring(2, INPUT.length());
        }
        String description = str.split("/by")[0];
        String dateAndTime = str.split("/by ")[1];
        Deadline deadline = new Deadline(description, dateAndTime);
        LIST.add(deadline);
        return UI.showAdd(deadline) + UI.showTaskSize(LIST.size());
    }

    /**
     * creates an event task.
     * @return a String showing the newly created task.
     * @throws EmptyDescriptionException if description of task is empty.
     */
    public String eventCommand() throws EmptyDescriptionException {
        String str = "";
        if (INPUT.startsWith("event")) {
            str = INPUT.replace("event", "");
        } else if (INPUT.startsWith("e ")) {
            str = INPUT.substring(2, INPUT.length());
        }
        String description = str.split("/from")[0];
        String temp = str.split("/from")[1];
        String from = temp.split("/to")[0];
        String to = temp.split("/to")[1];
        Event event = new Event(description, from, to);
        LIST.add(event);
        return UI.showAdd(event) + UI.showTaskSize(LIST.size());
    }

    /**
     * deletes a task.
     * @return a String showing which task is deleted.
     */
    public String deleteCommand() {
        String str = INPUT.replace("delete", "");
        int index = Integer.parseInt(str);
        index--;
        Task removedTask = LIST.get(index);
        LIST.remove(index);
        return UI.showDelete(removedTask);
    }

    /**
     * finds a task that contains a keyword.
     * @return a String of tasks that contains the keyword.
     */
    public String findCommand() {
        String keyword = INPUT.split(" ", 2)[1];
        ArrayList<Task> matchingTaskList = new ArrayList<>();
        for (Task task : LIST) {
            if (task.toString().contains(keyword)) {
                matchingTaskList.add(task);
            }
        }
        TaskList matches = new TaskList(matchingTaskList);
        assert matches.size() < LIST.size() : "list of filtered task should not be bigger than actual task list";
        String result = "Here are the matching tasks in your list:\n" + matches;
        return result;
    }

    /**
     * lets the chatbot say bye.
     * @return a String containing the bye message.
     */
    public String byeCommand() {
        return UI.showBye();
    }
}
