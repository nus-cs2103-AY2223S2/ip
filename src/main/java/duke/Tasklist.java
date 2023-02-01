package duke;

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
     * @param load the TaskList that was locally stored in the harddisk.
     */
    public Tasklist(ArrayList<Task> load) {
        storage = load;
    }

    /**
     * Updates the status of the current task, where users get to mark a task when completed, unmark a task, or even delete a task if
     * the task is no longer necessary to them.
     * @param request the type of request that the user requested for.
     * @param position position of the current task in the tasklist.
     */
    public void updateTask(String request, int position) {
        switch(request) {
            case "mark":
                storage.get(position).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + "[" + storage.get(position).getStatusIcon() + "] " + storage.get(position).description);
                break;
            case "unmark":
                storage.get(position).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:\n" + "[" + storage.get(position).getStatusIcon() + "] " + storage.get(position).description);
                break;
            case "delete":
                System.out.println("Noted. I've removed this task:\n" + storage.get(position - 1).toString() + "\nNow you have " + (storage.size() - 1) + " tasks in the list");
                storage.remove(position);
        }
    }

    /**
     * Prints all the tasks that are available in the current Tasklist to console.
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < storage.size(); i++) {
            System.out.println((i + 1) + "." + storage.get(i).toString());
        }
    }

    /**
     * Adds tasks to the Tasklist.
     * @param type the type of task to be added.
     * @param content the user input that they typed in the command line.
     */
    public void addingActivities(String type, String content) {
        switch (type) {
            case "todo":
                Todo todoTask = new Todo(content.substring(5), content);
                storage.add(todoTask);
                System.out.println("Got it. I've added this task:\n  " + todoTask.toString() + "\nNow you have " + storage.size() + " tasks in the list");
                break;
            case "deadline":
                int position = content.indexOf("/by ");
                Deadline deadlineTask = new Deadline(content.substring(9, position), content, content.substring(position + 4));
                storage.add(deadlineTask);
                System.out.println("Got it. I've added this task:\n  " + deadlineTask.toString() + "\nNow you have " + storage.size() + " tasks in the list");
                break;
            case "event":
                int position1 = content.indexOf("/from ");
                int position2 = content.indexOf("/to ");
                Event eventTask = new Event(content.substring(6, position1), content, content.substring(position1 + 6, position2), content.substring(position2 + 4));
                storage.add(eventTask);
                System.out.println("Got it. I've added this task:\n  " + eventTask.toString() + "\nNow you have " + storage.size() + " tasks in the list");
                break;
        }
    }

    /**
     * Returns an ArrayList that currently contains all the task that have been added or marked.
     * @return The ArrayList containing all the tasks.
     */

    public ArrayList<Task> getList() {
        return storage;
    }
}
