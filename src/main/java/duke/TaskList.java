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
     *
     * @param input input command to add a To Do
     * @return new task list after adding the To Do
     */
    public String addToDo(String input) {
        String details = input.split(" ", 2)[1];
        String response = "";
        try {
            if (details.equals("")) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            Todo newTodo = new Todo(details);
            tasks.add(newTodo);
            response = "Got it. I've added this task:" + '\n' + newTodo + '\n' + "Now you have "
                    + tasks.size() + " tasks in the list";
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }

    /**
     *
     * @param input input command to add a deadline
     * @return new task list after adding the deadline
     */
    public String addDeadline(String input) {
        String details = input.split(" ", 2)[1];
        String response = "";
        try {
            String[] detailsAndDate = details.split(" /by ");
            String description = detailsAndDate[0];
            LocalDate date = LocalDate.parse(detailsAndDate[1]);
            Deadline newDeadline = new Deadline(description, date);
            tasks.add(newDeadline);
            response = "Got it. I've added this task:" + '\n' + newDeadline + '\n' + "Now you have "
                    + tasks.size() + " tasks in the list";
        } catch (DateTimeParseException e) {
            response = "Please input date in YYYY-MM-DD format!";
        }
        return response;
    }

    /**
     *
     * @param input input command to add an event
     * @return new task list after adding the event
     */
    public String addEvent(String input) {
        String details = input.split(" ", 2)[1];
        String response;
        String[] detailsAndTime = details.split(" /from ");
        String description = detailsAndTime[0];
        String[] time = detailsAndTime[1].split(" /to ");
        String to = time[0];
        String from = time[1];
        Event newEvent = new Event(description, to, from);
        tasks.add(newEvent);
        response = "Got it. I've added this task:" + '\n' + newEvent + '\n' + "Now you have "
                + tasks.size() + " tasks in the list";
        return response;
    }

    /**
     * Returns a string representation of the task list
     * @return String string representation of the task list
     */
    public String getTaskList() {
        String res = "";
        if (tasks.isEmpty()) {
            return "You have no tasks as of now. Try adding some!";
        }
        for (int i = 0; i < tasks.size(); i ++) {
            res += Integer.toString(i + 1) + ". ";
            res += tasks.get(i).toString();
            res += '\n';
        }
        assert !res.isEmpty() : "Tasklist should not be empty!";
        return res;
    }

    /**
     * Marks a specific task as done
     * @param input input command to mark task
     * @return task list after marking the task
     */
    public String markTask(String input) {
        String details = input.split(" ", 2)[1];
        int index = Integer.parseInt(details);
        Task currTask = tasks.get(index - 1);
        currTask.mark();
        return "Nice! I've marked this task as done" + '\n' + currTask;
    }

    /**
     * Marks a specific task as not done
     * @param input input command to mark task
     * @return task list after un-marking the task
     */
    public String unmarkTask(String input) {
        String details = input.split(" ", 2)[1];
        int index = Integer.parseInt(details);
        Task currTask = tasks.get(index - 1);
        currTask.unMark();
        return "Nice! I've marked this task as not done yet" + '\n' + currTask;
    }

    /**
     * Deletes a task from the task list
     * @param input input command to delete task
     * @return String task list after deletion
     */
    public String deleteTask(String input) {
        String details = input.split(" ", 2)[1];
        int index = Integer.parseInt(details);
        Task currTask = tasks.get(index - 1);
        tasks.remove(index - 1);
        return "Noted. I've removed this task:" + '\n' + currTask  + '\n' + "Now you have " + tasks.size() + " tasks in the list";
    }

    /**
     * Returns the size of the task list
     * @return Integer size of task list
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns a task list filtered by a keyword
     * @param input input command to find a specific task with a keyword
     * @return String filtered task list
     */
    public String findTask(String input) {
        String keyword = input.split(" ", 2)[1];
        try {
            ArrayList<Task> res = new ArrayList<>();
            for (Task task : tasks) {
                if (task.getDescription().contains(keyword)) {
                    res.add(task);
                }
            }
            if (res.isEmpty()) {
                return "There are no matching tasks with the keyword: " + keyword;
            }
            TaskList result = new TaskList(res);
            assert !(result.getSize() == 0);
            return "Here are the matching tasks in your list:\n" + result.getTaskList();
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please input the keyword";
        }
    }

    public String markPriority(String input) {
        String response = "";
        try {
            String inputArr[] = input.split(" ", 3);
            String taskNum = inputArr[1];
            String priority = inputArr[2];
            int index = Integer.parseInt(taskNum);
            Task currTask = tasks.get(index - 1);
            currTask.assignPriority(priority);
            response = "Nice! I have assigned this task:\n" + currTask + "\nas " + priority + " priority";

        } catch (ArrayIndexOutOfBoundsException e) {
            response = "Please input a valid index";
        }
        return response;

    }

    /**
     * Converts task list to storage data
     * @return String task list in storage data format with dividers
     */
    public String toStorageData() {
        String storageData = "";
        for (Task task : tasks) {
            storageData += task.toStorageData();
            storageData += "\n";
        }
        return storageData;
    }
}
