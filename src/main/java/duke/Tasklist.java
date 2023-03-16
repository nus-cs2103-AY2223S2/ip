package duke;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Tasklist {
    private ArrayList<Task> storage;

    /**
     * Constructor to create a brand new TaskList if there is non stored locally.
     */
    public Tasklist() {
        storage = new ArrayList<Task>();
    }

    /**
     * Constructor that loads the TaskList that was previously stored locally in the harddisk.
     *
     * @param load the TaskList that was locally stored in the harddisk.
     */
    public Tasklist(ArrayList<Task> load) {
        storage = load;
    }

    /**
     * Updates the status of the current task, where users get to mark a task when completed, unmark a task, or even delete a task if
     * the task is no longer necessary to them.
     *
     * @param request  the type of request that the user requested for.
     * @param position position of the current task in the tasklist.
     */
    public String updateTask(String request, int position) {
        switch (request) {
        case "mark":
            storage.get(position).markAsDone();
            return "Nice! I've marked this task as done:\n" + "[" + storage.get(position).getStatusIcon() + "] " + storage.get(position).description;
        case "unmark":
            storage.get(position).markAsNotDone();
            return "OK, I've marked this task as not done yet:\n" + "[" + storage.get(position).getStatusIcon() + "] " + storage.get(position).description;
        case "delete":
            Task deletedTask = storage.get(position);
            storage.remove(position);
            return "Noted. I've removed this task:\n" + deletedTask.toString() + "\nNow you have " + (storage.size()) + " " + "tasks in the list";
        }
        return "";
    }

    /**
     * Prints all the tasks that are available in the current Tasklist to console.
     */
    public String printList() {
        String list = "Here are the tasks in your list:\n";
        for (int i = 0; i < storage.size(); i++) {
            list = list + (i + 1) + "." + storage.get(i).toString() + "\n";
        }
        list = list + " ";
        return list;
    }

    /**
     * Adds tasks to the Tasklist.
     *
     * @param type    the type of task to be added.
     * @param content the user input that they typed in the command line.
     */
    public String addingActivities(String type, String content) throws DateTimeParseException{
        switch (type) {
        case "todo":
            Todo todoTask = new Todo(content.substring(5), content);
            storage.add(todoTask);
            return "Got it. I've added this task:\n  " + todoTask.toString() + "\nNow you have " + storage.size() + " tasks in the list";
        case "deadline":
            if (!content.contains("/by")) {
                return "☹ OOPS!!! The syntax for deadline is wrong. Type /help for user guide.";
            }
            int position = content.indexOf("/by ");
            try {
                LocalDate.parse(content.substring(position + 4));
            } catch (DateTimeParseException e) {
                return "☹ OOPS!!! The date is in the wrong format. Type /help for user guide.";
            }
            Deadline deadlineTask = new Deadline(content.substring(9, position), content, content.substring(position + 4));
            storage.add(deadlineTask);
            return "Got it. I've added this task:\n  " + deadlineTask.toString() + "\nNow you have " + storage.size() + " tasks in the list";
        case "event":
            if (!(content.contains("/from")) || !(content.contains("/to"))) {
                return "☹ OOPS!!! The syntax for event is wrong. Type /help for user guide.";
            }
            int position1 = content.indexOf("/from ");
            int position2 = content.indexOf("/to ");
            Event eventTask = new Event(content.substring(6, position1), content, content.substring(position1 + 6, position2), content.substring(position2 + 4));
            storage.add(eventTask);
            return "Got it. I've added this task:\n  " + eventTask.toString() + "\nNow you have " + storage.size() + " tasks in the list";
        }
        return "";
    }

    public String findingActivities(String content) {
        int index = 1;
        String found = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < storage.size(); i++) {
            Task taskObtained = storage.get(i);
            if (taskObtained.getDescription().contains(content)) {
                if (taskObtained instanceof Todo) {
                    found = found + (index + "." + ((Todo) taskObtained).toString()) + "\n";
                    index++;
                } else if (taskObtained instanceof Deadline) {
                    found = found + (index + "." + ((Deadline) taskObtained).toString()) + "\n";
                    index++;
                } else {
                    found = found + index + "." + ((Event) taskObtained).toString() + "\n";
                    index++;
                }
            }
        }
        found = found + " ";
        return found;
    }

    /**
     * Returns an ArrayList that currently contains all the task that have been added or marked.
     *
     * @return The ArrayList containing all the tasks.
     */
    public ArrayList<Task> getList() {
        return storage;
    }

    public int taskSize() {
        return storage.size();
    }
}
