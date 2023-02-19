package duke;

import java.util.Scanner;

public class Parser {
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
     * Displays the current list of tasks
     */
    private String showList() {
        return ui.showTaskList(taskList);
    }

    /**
     * Marks the Task at a specified index as done
     * @param index the index of the Task to be marked as done
     */
    private String mark(int index) {
        taskList.mark(index);
        Task task = taskList.get(index);
        return ui.showMarkMessage(task, index);
    }

    /**
     * Marks the Task at a specified index as undone
     * @param index the index of the Task to be marked as undone
     */
    private String unmark(int index) {
        taskList.unmark(index);
        Task task = taskList.get(index);
        return ui.showUnmarkMessage(task, index);
    }

    /**
     * Deletes the Task at a specified index
     * @param index the index of the Task to be deleted
     */
    private String delete(int index) {
        Task deletedTask = taskList.delete(index);
        int len = taskList.size();
        return ui.showDeleteMessage(deletedTask, len);
    }

    /**
     * Adds a Todo to the TaskList
     * @param task the String representing the Todo to be added
     * @throws DukeException if the description of the Todo is empty
     */
    private void addTodo(String task) throws DukeException {
        String description = task.substring(5);
        if (description.trim().equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        assert description != null : "Description cannot be null";
        taskList.add(new Todo(description));
    }

    /**
     * Adds a Deadline to the TaskList
     * @param task the String representing the Deadline to be added
     */
    private void addDeadline(String task) {
        String[] arr = task.split("/");
        String description = arr[0].substring(9).trim();
        String by = arr[1].substring(3).trim();
        assert description != null : "Description cannot be null";
        assert by != null : "By cannot be null";
        taskList.add(new Deadline(description, by));
    }

    /**
     * Adds an Event to the TaskList
     * @param task the String representing the Event to be added
     */
    private void addEvent(String task) {
        assert task != null;
        String[] arr = task.split("/");
        String description = arr[0].substring(6).trim();
        String from = arr[1].substring(5).trim();
        String to = arr[2].substring(3).trim();
        assert description != null : "Description cannot be null";
        assert from != null : "From cannot be null";
        assert to != null : "To cannot be null";
        taskList.add(new Event(description, from, to));
    }

    /**
     * Finds an event with a given keyword in the TaskList
     * @param task the String representing the Task with the keyword to be found
     */
    private String find(String task) {
        String keyword = task.substring(5);
        TaskList matchingTasks = taskList.find(keyword);
        return ui.showTaskList(matchingTasks);
    }

    /**
     * Parses a single line of user input
     * @param task the String containing a single line of user input
     * @throws DukeException if an invalid command is entered
     */
    public String parse(String task) throws DukeException {
        if (task.equals("list")) {
            return showList();
        } else if (task.startsWith("mark")) {
            int index = Integer.parseInt(task.substring(5)) - 1;
            return mark(index);
        } else if (task.startsWith("unmark")) {
            int index = Integer.parseInt(task.substring(7)) - 1;
            return unmark(index);
        } else if (task.startsWith("delete")) {
            int index = Integer.parseInt(task.substring(7)) - 1;
            return delete(index);
        } else if (task.startsWith("find")) {
            return find(task);
        } else {
            if (task.startsWith("todo")) {
                addTodo(task);
            } else if (task.startsWith("deadline")) {
                addDeadline(task);
            } else if (task.startsWith("event")) {
                addEvent(task);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            int len = taskList.size();
            Task t = taskList.get(len - 1);
            return ui.showAddMessage(t, len);
        }
    }

    /**
     * Parses user input until the termination command "bye" is entered
     */
    public void parseAll() {
        Scanner sc = new Scanner(System.in);

        try {
            while (true) {
                String task = sc.nextLine();
                if (task.equals("bye")) {
                    ui.showByeMessage();
                    break;
                } else {
                    parse(task);
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
