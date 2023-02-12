package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

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
    static final Pattern DEADLINE_PATTERN = Pattern.compile("(.+)/by (.+)");
    static final Pattern EVENT_PATTERN = Pattern.compile("(.+)/from (.+) /to (.+)");
    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

    private final Ui ui = new Ui();
    public static ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
    }

    void listTask() {
        if (tasks.size() == 0) {
            System.out.println("You dont have any tracked tasks");
        } else {
            System.out.println("Your current tracked tasks: ");
            for (int i = 0; i < tasks.size(); i++) {
                Task curr = tasks.get(i);
                System.out.println((i + 1) + "." + curr);
            }
        }
    }

    void markTaskDone(String body) {
        body = body.substring(1);
        int index = Integer.parseInt(body) - 1;
        tasks.get(index).toggleDone();
        System.out.println("Marked task as done:\n [" + tasks.get(index).getDoneStatus() + "] " + tasks.get(index).getDesc());
    }

    void markTaskNotDone(String body) {
        body = body.substring(1);
        int index = Integer.parseInt(body) - 1;
        tasks.get(index).toggleNotDone();
        System.out.println("Marked task as not done:\n [" + tasks.get(index).getDoneStatus() + "] " + tasks.get(index).getDesc());

    }

    void deleteTask(String body) {
        try {
            body = body.substring(1);
            int i = Integer.parseInt(body) - 1;
            Task temp = tasks.get(i);
            tasks.remove(temp);
            ui.printDelete(temp, tasks.size());
        } catch (Exception e) {
            System.out.println(ui.ERROR_DELETE_TASK);
        }
    }

    void addTodo(String body){
        try {
            body = body.substring(1);
            ToDo curr = new ToDo(body, false);
            tasks.add(curr);
            ui.printNotif(curr, tasks.size());
        } catch (Exception e) {
            System.out.println(ui.ERROR_EMPTY_TODO);
        }
    }

    void addDeadline(String body){
        body = body.substring(1);
        Matcher dlMatcher = DEADLINE_PATTERN.matcher(body);
        if (dlMatcher.matches()) {
            String desc = dlMatcher.group(1);
            String deadlineDay = dlMatcher.group(2);
            LocalDateTime deadlineDayParsed = LocalDateTime.parse(deadlineDay, DATE_TIME_FORMATTER);

            Deadline dl = new Deadline(desc, false, deadlineDayParsed);
            tasks.add(dl);
            ui.printNotif(dl, tasks.size());
        }
    }

    void addEvent(String body) {
        body = body.substring(1);
        Matcher eMatcher = EVENT_PATTERN.matcher(body);
        if (eMatcher.matches()) {
            String desc = eMatcher.group(1);
            String from = eMatcher.group(2);
            String to = eMatcher.group(3);

            LocalDateTime fromParsed = LocalDateTime.parse(from, DATE_TIME_FORMATTER);
            LocalDateTime toParsed = LocalDateTime.parse(to, DATE_TIME_FORMATTER);

            Event dl = new Event(desc, false, fromParsed, toParsed);
            tasks.add(dl);
            ui.printNotif(dl, tasks.size());
        }
    }

    /**
     *
     * @param body string to find
     */
    void find(String body) {
        boolean found = false;

        System.out.println("-------------------------------------------------");
        System.out.println("Matching tasks I've found in your list: ");
        for (Task temp : tasks) {
            if (temp.getDesc().contains(body)) {
                found = true;
                System.out.println(temp);
            }
        }

        if(!found){
            System.out.println("No tasks matches your search :(");
        }
        System.out.println("-------------------------------------------------");
    }

    /**
     * getter for task list
     * @return task list
     */
    ArrayList<Task> getTaskList() {
        return tasks;
    }
}
