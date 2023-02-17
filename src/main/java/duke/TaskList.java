package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class deals with any interaction with the task list (insert, update, delete)
 *
 * @author He Shuimei
 * @version 0
 */
public class TaskList {
    public static final Pattern DEADLINE_PATTERN = Pattern.compile("(.+)/by (.+)");
    public static final Pattern EVENT_PATTERN = Pattern.compile("(.+)/from (.+) /to (.+)");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    private static ArrayList<Task> tasks = new ArrayList<>();
    private final Ui ui = new Ui();

    public TaskList(ArrayList<Task> tasks)  {
        TaskList.tasks = tasks;
    }

    public TaskList() {
        TaskList.tasks = new ArrayList<Task>();
    }

    public String listTask() {
        if (tasks.size() == 0) {
            return "You dont have any tracked tasks";
        } else {
            String output = "Your current tracked tasks: \n";
            for (int i = 0; i < tasks.size(); i++) {
                Task curr = tasks.get(i);
                output = output + (i + 1) + " . " + curr + "\n";
            }
            return output;
        }
    }

    public String markTaskDone(String body) {
        int index = Integer.parseInt(body) - 1;
        tasks.get(index).toggleDone();
        return "Marked task as done:\n [X] " + tasks.get(index).getDesc();
    }

    public String markTaskNotDone(String body) {
        int index = Integer.parseInt(body) - 1;
        tasks.get(index).toggleNotDone();
        return "Marked task as not done:\n [ ] " + tasks.get(index).getDesc();

    }

    public String deleteTask(String body) {
        try {
            int i = Integer.parseInt(body) - 1;
            Task temp = tasks.get(i);
            tasks.remove(temp);
            return ui.printDeleteMessage(temp, tasks.size());
        } catch (Exception e) {
            return ui.ERROR_DELETE_TASK;
        }
    }

    public String addTodo(String body) {
        try {
            ToDo curr = new ToDo(body, false);
            tasks.add(curr);
            return ui.printNotification(curr, tasks.size());
        } catch (Exception e) {
            return ui.ERROR_EMPTY_TODO;
        }
    }

    public String addDeadline(String body) {
        try {
            Matcher dlMatcher = DEADLINE_PATTERN.matcher(body);
            if (dlMatcher.matches()) {
                String desc = dlMatcher.group(1);
                String deadlineDay = dlMatcher.group(2);
                LocalDateTime deadlineDayParsed = LocalDateTime.parse(deadlineDay, DATE_TIME_FORMATTER);

                Deadline dl = new Deadline(desc, false, deadlineDayParsed);
                tasks.add(dl);
                return ui.printNotification(dl, tasks.size());
            } else {
                return ui.ERROR_WRONG_DATE_FORMAT;
            }
        } catch (Exception e) {
            return "Error adding Deadline: " + e;
        }
    }

    public String addEvent(String body) {
        try {
            Matcher eMatcher = EVENT_PATTERN.matcher(body);
            if (eMatcher.matches()) {
                String desc = eMatcher.group(1);
                String from = eMatcher.group(2);
                String to = eMatcher.group(3);

                LocalDateTime fromParsed = LocalDateTime.parse(from, DATE_TIME_FORMATTER);
                LocalDateTime toParsed = LocalDateTime.parse(to, DATE_TIME_FORMATTER);

                Event dl = new Event(desc, false, fromParsed, toParsed);
                tasks.add(dl);
                return ui.printNotification(dl, tasks.size());
            } else {
                return ui.ERROR_WRONG_DATE_FORMAT;
            }
        } catch (Exception e) {
            return "Error adding Event: " + e;
        }
    }

    /**
     * Searches for task description that matches body
     *
     * @param body string to find
     */
    public String find(String body) {
        boolean found = false;
        String output = "Matching tasks I've found in your list: \n";

        for (Task temp : tasks) {
            if (temp.getDesc().contains(body)) {
                found = true;
                output = output + temp + "\n";
            }
        }

        if (!found) {
            output += "No tasks matches your search :(";
        }

        return output;
    }

    public String unknownCommand(String command) {
        return ui.ERROR_UNKNOWN_COMMAND + " : " + command;
    }
    /**
     * getter for task list
     * @return task list
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }
}
