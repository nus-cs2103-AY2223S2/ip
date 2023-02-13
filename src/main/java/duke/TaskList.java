package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList (ArrayList<Task> tasks) {
        for (Task t : tasks) {
            this.tasks.add(t);
        }
    }

    public TaskList () {

    }
    public void addTask(String details, String taskType) {
        if (taskType.equals("todo")) {
            try {
                if (details.equals("")) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                Todo newTodo = new Todo(details);
                tasks.add(newTodo);
                System.out.println("Got it. I've added this task:" + '\n' + newTodo + '\n' + "Now you have " + tasks.size() + " tasks in the list");
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
                System.out.println("Got it. I've added this task:" + '\n' + newDeadline + '\n' + "Now you have " + tasks.size() + " tasks in the list");
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
            System.out.println("Got it. I've added this task:" + '\n' + newEvent + '\n' + "Now you have " + tasks.size() + " tasks in the list");
        }
    }
    public void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
    }

    public void markTask (int index) {
        Task currTask = tasks.get(index - 1);
        currTask.mark();
        System.out.println("Nice! I've marked this task as done" + '\n' + currTask);
    }

    public void unmarkTask (int index) {
        Task currTask = tasks.get(index - 1);
        currTask.unMark();
        System.out.println("Nice! I've marked this task as not done yet" + '\n' + currTask);
    }

    public void deleteTask (int index) {
        Task currTask = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println("Noted. I've removed this task:" + '\n' + currTask  + '\n' + "Now you have " + tasks.size() + " tasks in the list");
    }

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
