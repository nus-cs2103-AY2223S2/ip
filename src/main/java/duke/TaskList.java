package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;

/**
 * Task List class
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for instantiating a task list
     * @param tasks array of tasks to be added to the task list
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>();
        for (Task t : tasks) {
            this.tasks.add(t);
        }
    }

    /**
     * Constructor for instantiating an empty task list
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list
     * @param details details of the task
     * @param taskType type of task to be added (todo, deadline, event)
     */
    public void addTask(String details, String taskType) {
        if (taskType.equals("todo")) {
            try {
                if (details.equals("")) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                Todo newTodo = new Todo(details);
                tasks.add(newTodo);
                System.out.println("Got it. I've added this task:" + '\n' + newTodo + '\n' + "Now you have "
                        + tasks.size() + " tasks in the list");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        } else if (taskType.equals("deadline")) {
            try {
                String[] detailsAndDate = details.split(" /by ");
                String description = detailsAndDate[0];
                LocalDate date = LocalDate.parse(detailsAndDate[1]);
                Deadline newDeadline = new Deadline(description, date);
                tasks.add(newDeadline);
                System.out.println("Got it. I've added this task:" + '\n' + newDeadline + '\n' + "Now you have "
                        + tasks.size() + " tasks in the list");
            } catch (DateTimeParseException e) {
                System.out.println("Please input date in YYYY-MM-DD format!");
            }
        } else if (taskType.equals("event")) {
            String[] detailsAndTime = details.split(" /from ");
            String description = detailsAndTime[0];
            String[] Time = detailsAndTime[1].split(" /to ");
            String To = Time[0];
            String From = Time[1];
            Event newEvent = new Event(description, To, From);
            tasks.add(newEvent);
            System.out.println("Got it. I've added this task:" + '\n' + newEvent + '\n' + "Now you have "
                    + tasks.size() + " tasks in the list");
        }
    }

    /**
     * Console logs the task list
     */
    public void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
    }

    /**
     * Marks a specific task as done
     * @param index index of task to be marked as done
     */
    public void markTask(int index) {
        Task currTask = tasks.get(index - 1);
        currTask.mark();
        System.out.println("Nice! I've marked this task as done" + '\n' + currTask);
    }

    /**
     * Marks a specific task as not done
     * @param index index of task to be marked as not done
     */
    public void unmarkTask(int index) {
        Task currTask = tasks.get(index - 1);
        currTask.unMark();
        System.out.println("Nice! I've marked this task as not done yet" + '\n' + currTask);
    }

    /**
     * Deletes a task from the task list
     * @param index index of task to be deleted
     */
    public void deleteTask(int index) {
        Task currTask = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println("Noted. I've removed this task:" + '\n' + currTask  + '\n' + "Now you have " + tasks.size() + " tasks in the list");
    }

    public void findTask(String keyword) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                res.add(task);
            }
        }
        TaskList result = new TaskList(res);
        System.out.println("Here are the matching tasks in your list:\n" + result.tasksToStringFormat());
    }

    /**
     * Returns a string representation of the task list
     * @return String string representation of the task list
     */
    public String tasksToStringFormat() {
        String res = "";
        for (int i = 0; i < tasks.size(); i ++) {
            res += Integer.toString(i + 1) + ". ";
            res += tasks.get(i).toString();
            res += '\n';
        }
        return res;
    }
}
