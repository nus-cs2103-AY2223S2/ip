package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a persistent parser, parses whatever input is thrown at it.
 */
class Parser {

    private TaskList taskList;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private DateTimeFormatter altFormatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mma");

    /**
     * Initiates parser with a taskList.
     *
     * @param taskList takes in a taskList to be assigned to itself.
     */
    Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Getter for taskList.
     *
     * @return taskList returns this.taskList.
     */
    TaskList getTaskList() {
        return taskList;
    }

    /**
     * Main parsing happens here.
     *
     * @param input takes in an input to parse.
     * @return String returns output to be printed.
     */
    String parse(String input) {
        if (input.equals("bye")) {
            return sayBye();
        } else if (input.equals("list")) {
            return returnList(taskList);
        } else if (input.startsWith("sort")) {
            return sortTaskList();
        } else if (input.startsWith("mark")) {
            return mark(input);
        } else if (input.startsWith("unmark")) {
            return unmark(input);
        } else if (input.startsWith("delete")) {
            return delete(input);
        } else if (input.startsWith("find")) {
            return find(input);
        } else {
            return addTask(input)[1];
        }
    }

    /**
     * Formats the taskList for output.
     *
     * @param taskList takes in a taskList to be formatter.
     * @return String returns formatted taskList.
     */
    String returnList(List<Task> taskList) {
        StringBuilder out = new StringBuilder();
        int size = taskList.size();
        if (taskList.size() > 0) {
            for (int i = 1; i < size; i++) {
                out.append(String.format("%d. %s\n", i, taskList.get(i - 1)));
            }
            out.append(String.format("%d. %s", size, taskList.get(size - 1)));
        } else {
            out.append("Your list is empty hehe.");
        }
        return out.toString();
    }

    /**
     * Sorts taskList alphabetically.
     *
     * @return String returns formatted taskList after sorting.
     */
    String sortTaskList() {
        Collections.sort(taskList);
        return returnList(taskList);
    }

    /**
     * Sets status.
     *
     * @param index index of task to set the status of.
     * @param isDone boolean to set the status for that task.
     */
    void setStatus(String index, Boolean isDone) {
        int i = Integer.parseInt(index) - 1;
        Task task = taskList.get(i);
        task.setStatus(isDone);
    }

    /**
     * Marks task as done.
     *
     * @param input index of task to mark as done.
     * @return String Indicates task is done.
     */
    String mark(String input) {
        int i = Integer.parseInt(input.split(" ", 2)[1]) - 1;
        Task task = taskList.get(i);
        task.mark();
        return String.format("\tNice! I've marked this task as done:\n\t  %s", task);
    }

    /**
     * Marks task as undone.
     *
     * @param input index of task to mark as undone.
     * @return String Indicates task is not done.
     */
    private String unmark(String input) {
        int i = Integer.parseInt(input.split(" ", 2)[1]) - 1;
        Task task = taskList.get(i);
        task.unmark();
        return String.format("\tNice! I've marked this task as not done yet:\n\t  %s", task);
    }

    /**
     * Deletes task from taskList.
     *
     * @param input index of task to delete.
     * @return String Indicates task is deleted.
     */
    private String delete(String input) {
        int i = Integer.parseInt(input.split(" ", 2)[1]) - 1;
        Task task = taskList.remove(i);
        String out = String.format("\tNoted. I've removed this task:\n\t  %s", task);
        out += String.format("\nNow you have %d tasks in the list.", taskList.size());
        return out;
    }

    /**
     * Finds task with similar description.
     *
     * @param input description to find.
     * @return String List of tasks matching.
     */
    private String find(String input) {
        String s = input.split("find ", 2)[1];
        List<Task> filteredTasks = taskList.stream().filter((t)
                -> t.getTaskDescription().contains(s))
                .collect(Collectors.toList());
        StringBuilder out = new StringBuilder("Here are the matching tasks in your list:\n");
        out.append(returnList(filteredTasks));
        return out.toString();
    }

    /**
     * Joe says bye.
     *
     * @return String Saying bye.
     */
    String sayBye() {
        return "\tBye. Hope to see you again soon!";
    }

    /**
     * Adds a task.
     *
     * @param input input to parse and create task.
     * @return String[] array of taskList size and task formatted as String.
     */
    String[] addTask(String input) {
        String[] split = input.split(" ", 2);
        String taskType = split[0];
        try {
            switch (taskType) {
            case "t": //fallthrough
            case "todo": {
                return addToDo(split);
            }
            case "d": {
                return addDeadline(split, true);
            }
            case "deadline": {
                return addDeadline(split, false);
            }
            case "e": {
                return addEvent(split, true);
            }
            case "event": {
                return addEvent(split, false);
            }
            default:
                DukeException.rethrow("UnknownCommand");
            }
        } catch (Exception e) {
            return new String[]{"", "Oops your command is not recognized"};
        }
        return null;
    }

    /**
     * Formats a task String.
     *
     * @param task task to format.
     * @return String Nicely formatted task.
     */
    String formatAddTask(Task task) {
        String out = String.format("Added:\n\t%s", task);
        out += String.format("\nNow you have %d tasks in the list.", taskList.size());
        return out;
    }

    /**
     * Adds a todo.
     *
     * @param input input to parse and create deadline.
     * @return String[] array of taskList size and todo formatted as String.
     */
    String[] addToDo(String[] input) throws Exception {
        if (input[1].equals("")) {
            DukeException.rethrow("ToDoException");
        }
        Task task = new ToDo(input[1]);
        taskList.add(task);
        return new String[]{String.valueOf(taskList.size()), formatAddTask(task)};
    }

    /**
     * Adds a deadline.
     *
     * @param input input to parse and create deadline.
     * @param isAlt flag indicating which formatter and splitter to use.
     * @return String[] array of taskList size and deadline formatted as String.
     */
    String[] addDeadline(String[] input, Boolean isAlt) {
        DateTimeFormatter dtFormat;
        String[] s;
        if (isAlt) {
            dtFormat = altFormatter;
            s = input[1].split(" by: ");
        } else {
            dtFormat = formatter;
            s = input[1].split(" /by ");
        }
        LocalDateTime byTime = LocalDateTime.parse(s[1], dtFormat);
        Task task = new Deadline(s[0], byTime);
        taskList.add(task);
        return new String[]{String.valueOf(taskList.size()), formatAddTask(task)};
    }

    /**
     * Adds an event.
     *
     * @param input input to parse and create event.
     * @param isAlt flag indicating which formatter and splitter to use.
     * @return String[] array of taskList size and event formatted as String.
     */
    String[] addEvent(String[] input, Boolean isAlt) {
        DateTimeFormatter dtFormat;
        String[] s;
        String taskDescription;
        if (isAlt) {
            dtFormat = altFormatter;
            s = input[1].split(" from: ", 2);
            taskDescription = s[0];
            s = s[1].split(" to: ");
        } else {
            dtFormat = formatter;
            s = input[1].split(" /from ", 2);
            taskDescription = s[0];
            s = s[1].split(" /to ");
        }
        LocalDateTime fromTime = LocalDateTime.parse(s[0], dtFormat);
        LocalDateTime toTime = LocalDateTime.parse(s[1], dtFormat);
        Task task = new Event(taskDescription, fromTime, toTime);
        taskList.add(task);
        return new String[]{String.valueOf(taskList.size()), formatAddTask(task)};
    }
}
