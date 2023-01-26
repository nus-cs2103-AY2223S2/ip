package duke;

import duke.task.Task;

public class Ui {
    /** 
     * Outputs given string with formatting.
     * 
     * @param string String to be outputted.
     */
    private static void output(String string) {
        System.out.println("____________________________________________________________\n" +
                            "  " + string + 
                            "____________________________________________________________\n");
    }

    /** Outputs welcome message. */
    protected static void welcomeMsg() {
        output("Hello! I'm Duke\n  What can I do for you?\n");
    }
    
    // Outputs exit message.
    protected static void exitMsg() {
        output("Bye. Hope to see you again soon!\n");
    }


    /** 
     * Outputs success message when task has been added to task list.
     * 
     * @param task Message regarding the task.
     * @param taskListSize Size of task list.
     */
    protected static void addTaskMsg(Task task, int taskListSize) {
        output("Got it. I've added this task:\n    " 
                + task + "\n  "
                + "Now you have " + taskListSize + (taskListSize == 1 ? " task " : " tasks ") + "in the list.\n");
    }

    /** 
     * Outputs success message when task has been removed from task list.
     * 
     * @param task Message regarding the task.
     * @param taskListSize Size of task list.
     */
    protected static void removeTaskMsg(Task task, int taskListSize) {
        output("Noted. I've removed this task:\n    " 
                + task + "\n  "
                + "Now you have " + taskListSize + (taskListSize == 1 ? " task " : " tasks ") + "in the list.\n");
    }

    /** 
     * Outputs all the tasks stored in task list.
     * 
     * @param listOfTasks String of the list of tasks.
     */
    protected static void listTasksMsg(String listOfTasks) {
        String listMessage = "Here are the tasks in your list:\n";
        output(listMessage + listOfTasks);
    }

    /** 
     * Outputs success message when task has been marked.
     * 
     * @param task Message regarding the task.
     */
    protected static void markTaskMsg(Task task) {
        output("Nice! I've marked this task as done:\n    " + task + "\n");
    }

    /** 
     * Outputs success message when task has been unmarked.
     * 
     * @param task Message regarding the task.
     */
    protected static void unmarkTaskMsg(Task task) {
        output("OK, I've marked this task as not done yet:\n    " + task + "\n");
    }

    /** Handles unknown input. */
    protected static void unknownInputMsg() {
        try {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        } catch (DukeException e) {
            errorMsg(e.getMessage());
        }
    }

    /** 
     * Outputs error message.
     * 
     * @param message Error message.
     */
    protected static void errorMsg(String message) {
        output(message);
    }
}