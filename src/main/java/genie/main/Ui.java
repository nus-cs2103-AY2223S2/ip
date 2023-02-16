package genie.main;

import genie.task.Task;

import java.util.ArrayList;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private StringBuilder response;
    /**
     * A constructor for Ui class.
     */
    public Ui() {
        response = new StringBuilder();
    }

    /**
     * Appends greeting for new users.
     */
    public void appendNewUserGreeting() {
        response.append("Hello! This is Genie, your personal task tracker!\n\n");
        appendGreetingHelpMessage();
    }

    /**
     * Appends greeting for old users.
     * @param loadedTasks
     */
    public void appendOldUserGreeting(ArrayList<String> loadedTasks) {
        response.append("Hey, glad to have you back!\n\n");
        appendLoadedTasks(loadedTasks);
        appendCustomMessage("\nNow, what can I do for you?");
    }

    /**
     * Prints task list if it is not empty. Otherwise, prints an empty task message.
     * @param tasks
     */
    public void printList(ArrayList<Task> tasks) {
        if (tasks.size() > 0) {
            response.append("Here are the tasks in your list:\n");
            for (int i = 1; i <= tasks.size(); i++) {
                Task t = tasks.get(i - 1);
                String numberedTaskInfo = appendNumberedTaskInfo(i, t);
                response.append(numberedTaskInfo);
            }
        } else {
            showEmptyListMessage();
        }
    }

    /**
     * Prints mark done message.
     * @param t task to be marked done
     */
    public void showMarkDoneMessage(Task t) {
        response.append("Nice! I've marked this task as done:\n" + "  " + t.toString() + "\n");
    }

    /**
     * Prints add task message.
     * @param t task to be added
     * @param size number of tasks in the list
     */
    public void appendAddTaskMessage(Task t, int size) {
        response.append("Got it. I've added this task:\n  " + t.toString() + "\n");
        response.append("Now you have " + size + " task(s) in the list.");
    }

    /**
     * Prints unmark done message.
     * @param t task to be unmarked done
     */
    public void appendUnmarkDoneMessage(Task t) {
        response.append("Okay, I've marked this task as not done yet:\n" + "  " + t.toString() + "\n");
    }
    /**
     * Prints delete task message.
     * @param t task to be deleted
     * @param size number of tasks in the list
     */
    public void showDeleteTaskMessage(Task t, int size) {
        response.append("Noted. I've removed this task:\n" + "  " + t.toString() +
                "\nNow you have " + size + " tasks in the list.\n");
    }
    /**
     * Prints add farewell message.
     */
    public void showFarewellMessage() {
        response.append("Bye bye now, I hope you had a productive day!\n");
    }

    /**
     * Returns general error message.
     */
    public void showErrorMessage() {
        response.append("Something went wrong here xx...\n");
    }

    /**
     * Prints empty list message.
     */
    public void showEmptyListMessage() {
        response.append("Your task list is currently empty! Let's get started! ^-^\n");
    }

    /**
     * Appends task list that matches specified keyword.
     * @param tasks
     */
    public void printMatchingTaskList(ArrayList<Task> tasks) {
        if(tasks.size() > 0) {
            response.append("Here are the matching tasks in your list:\n");
            for(int i = 1; i <= tasks.size(); i++) {
                Task t = tasks.get(i - 1);
                String numberedTaskInfo = appendNumberedTaskInfo(i, t);
                response.append(numberedTaskInfo);
            }
        } else {
            showEmptyMatchingTasksMessage();
        }
    }

    /**
     * Appends empty matching tasks message.
     */
    public void showEmptyMatchingTasksMessage() {
        response.append("There are no matching tasks for your search :(\n");
    }

    /**
     * Appends help message.
     */
    public void appendHelpMessage() {
        response.append("No problem! Here is a list of commands I can recognise:\n");
        response.append("\n");
        response.append(showAllCommands());
    }

    /**
     * Appends help message when Genie greets a new user.
     */
    public void appendGreetingHelpMessage() {
        response.append("To get started, here is a list of commands that I recognise:\n");
        response.append(showAllCommands());
    }

    public String getResponse() {
        return response.toString();
    }

    /**
     * Clears responses appended to it.
     */
    public void clearResponse() {
        response.setLength(0);
    }

    /**
     * Appends index number to the task.
     * @param index
     * @param t task
     * @return task with index number
     */
    public String appendNumberedTaskInfo(int index, Task t) {
        String numberedTaskInfo = index + ". " + t.toString() + "\n";
        return numberedTaskInfo;
    }

    /**
     * Shows all recognisable commands.
     * @return all commands with help usage
     */
    public String showAllCommands() {
        String allCommands = "• todo <task>\n" +
                "• event <task> /from <time> /to <time>\n" +
                "• deadline <task> /by <time>\n" +
                "    - <time> (optional): YYYY-MM-DD HH:MM\n" +
                "\n" +
                "• delete <list number>\n" +
                "    - deletes that task on the list\n" +
                "• mark <list number>\n" +
                "    - marks that task as done\n" +
                "• unmark <list number>\n" +
                "    - marks that task as undone\n" +
                "• list\n" +
                "    - shows your consolidated task list\n" +
                "• find <keyword>\n" +
                "    - finds all tasks with the specified keyword\n" +
                "• help\n" +
                "    - shows help page\n" +
                "• bye\n" +
                "    - saves your task list and terminates the application";
        return allCommands;
    }

    /**
     * Appends loaded tasks.
     * @param loadedTasks
     */
    public void appendLoadedTasks(ArrayList<String> loadedTasks) {
        String strTasks = "Here is a record of your task list from where you had previously left off:\n";
        for (int i = 0; i < loadedTasks.size(); i++) {
            String task = loadedTasks.get(i);
            int number = i + 1;
            strTasks += "  " + number + ". " + task + "\n";
        }
        response.append(strTasks);
    }

    /**
     * Appends custom message.
     * @param customMessage
     */
    public void appendCustomMessage(String customMessage) {
        response.append(customMessage);
    }
}
