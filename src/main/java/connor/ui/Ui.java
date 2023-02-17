package connor.ui;

import connor.task.Task;

/**
 * Ui object to handle printing of messages.
 */
public class Ui {

    /**
     * Returns a message greeting the user.
     * To be printed at the start of program.
     *
     * @return String that greets the user.
     */
    public String greet() {
        return ("Hello! I'm Connor, the android sent by Cyberlife.\n"
                + "Please type in your command below.");
    }

    /**
     * Returns a string that greets the user depending on the user input.
     *
     * @param responseType the user input command.
     * @return String that greets the user depending on the user input command.
     */
    public String greetings(String responseType) {
        switch (responseType) {
        case "HI":
            return "Hi, I hope that you are having a nice day.";

        case "BYE":
            return "It was a good session Hank, Bye.";

        default:
            return "";
        }
    }

    /**
     * Returns a message informing the user of a successful adding of task.
     *
     * @param task task object added.
     * @param size size of the new list.
     * @return String that indicates successful addition of task.
     */
    public String addTaskMessage(Task task, int size) {
        String message = "I have added " + task.getTaskName() + " to my memory\n";
        message = message + "  " + task.toString() + "\n";
        message = message + "You have " + size + " tasks in the list";
        return message;
    }

    /**
     * Returns a message indicating all tasks have been cleared.
     *
     * @return String indicating all tasks have been cleared.
     */
    public String deleteAllMessage() {
        return "All tasks on the list have been cleared";
    }

    /**
     * Returns a message indicating a specific task have been deleted.
     *
     * @param task task to be deleted.
     * @param size size of the new list.
     * @return String indicating a successful deletion of task.
     */
    public String deleteTaskMessage(Task task, int size) {
        String message = "I have removed " + task.getTaskName() + " from my memory\n";
        message = message + "  " + task.toString() + "\n";
        message = message + "You have " + size + " tasks in the list";
        return message;
    }

    /**
     * Returns a message indicating a specific task have been marked done.
     *
     * @param task the string representation of the task that has been marked done.
     * @return String indicating a specific task have been marked done.
     */
    public String markDoneMessage(String task) {
        return "Understood, I have marked the task as done:\n"
                + "  " + task;
    }

    /**
     * Returns a message indicating a specific task have been marked undone.
     *
     * @param task the string representation of the task that has been marked undone.
     * @return String indicating a specific task have been marked undone.
     */
    public String markUndoneMessage(String task) {
        return "Understood, I have marked the task as undone:\n"
                + "  " + task;
    }

    /**
     * Returns a message indicating list has been successfully sorted.
     *
     * @return String indicating list has been successfully sorted.
     */
    public String sortMessage() {
        return "I have successfully sorted the tasks";
    }
}
