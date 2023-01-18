package duke.ui;

import duke.task.*;

public class Ui {

    /**
     * Default constructor.
     */
    public Ui() {
    }

    private String line_break = "\n\t ^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n";

    /**
     * Method for first greet message.
     */
    public void greet() {
        String logo = "             _____             _____   _    _              _   _ \n" +
                "     /\\     |_   _|           / ____| | |  | |     /\\     | \\ | |\n" +
                "    /  \\      | |    ______  | |      | |__| |    /  \\    |  \\| |\n" +
                "   / /\\ \\     | |   |______| | |      |  __  |   / /\\ \\   | . ` |\n" +
                "  / ____ \\   _| |_           | |____  | |  | |  / ____ \\  | |\\  |\n" +
                " /_/    \\_\\ |_____|           \\_____| |_|  |_| /_/    \\_\\ |_| \\_|\n" +
                "                                                                 \n" +
                "                                                                 ";
        System.out.println("\t Hello I'm\n" + logo);
        System.out.println("\t What can I do for you?" + line_break);
    }

    /**
     * Method for ending message.
     */
    public void end() {
        System.out.println(line_break +
                "\t Bye. See you next time! :)\n" +
                line_break);
    }

    /**
     * Method for message when adding task to taskList.
     *
     * @param task task to be added to list.
     * @param size size of list with new task.
     */
    public void addTask(Task task, int size) {
        System.out.println(line_break + "\t Adding the task:\n\t\t" + task +
                "\n\t You now have " + size + " task(s)." + line_break);
    }

    /**
     * Method for message when deleting task.
     *
     * @param item item to be deleted.
     * @param size size of list with the task included.
     */
    public void deleteMessage(Task item, int size) {
        System.out.println(line_break + "\t 1 less task! :)");
        System.out.println("\t\t" + item+ "\n\tNow you have " + (size - 1) + " tasks left!" + line_break);
    }

    /**
     * Method for message when marking task as done.
     *
     * @param item item to be marked as done.
     */
    public void markMessage(Task item) {
        System.out.println(line_break + "\t Great job completing your task! :)");
        System.out.println("\t\t" + item + line_break);
    }

    /**
     * Method for message when marking task as undone.
     *
     * @param item to be unmarked.
     */
    public void unmarkMessage(Task item) {
        System.out.println(line_break + "\t I see you want to redo this task...");
        System.out.println("\t\t" + item + line_break);
    }

    /**
     * Method for message when user asks for whole list.
     *
     * @param taskList list to be printed.
     */
    public void print(TaskList taskList) {
        System.out.println(line_break + "\tHere are all your tasks, good luck!");
        System.out.print(taskList.toString());
        System.out.println(line_break);
    }

    /**
     * Method for loading error on startup.
     */
    public void showLoadingError() {
        System.out.println(line_break + "\tLoading error :(");
        System.out.println(line_break);
    }

}
