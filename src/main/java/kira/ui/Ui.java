package kira.ui;

import java.util.List;

import kira.task.Task;

/**
 * Ui class manages user interactions, prettify output
 * to simulate a conversation with the user.
 * 
 * @author Eric Goh
 */
public class Ui {

    /**
     * Formats and prints the initial message of the bot.
     */
    public void startMsg() {
        String startMsg = "Hi! I am KiraBot~\n"
                + "How may I help you today?";
        Printer.printFormatReplyMsg(startMsg);
    }

    /**
     * Formats and prints the last message of the bot.
     */
    public void endMsg() {
        Printer.printFormatReplyMsg("Goodbye! Have a nice day~");
    }

    /**
     * Formats and prints any error message for the bot.
     * 
     * @param msg Error message for the output.
     */
    public void errMsg(String msg) {
        Printer.printFormatErrorMsg(msg);
    }

    /**
     * Formats and prints message for the list command.
     * 
     * @param taskList
     */
    public void listMsg(List<Task> taskList) {
        Printer.printFormatReplyMsg("Here are all the items stored~"
                + listOfTasks(taskList));
    }

    /**
     * Formats and prints message for the list command.
     * 
     * @param taskList
     */
    public void findMsg(List<Task> taskList, String keyword) {
        Printer.printFormatReplyMsg("Here are all the tasks matching " 
                + keyword 
                + "~"
                + listOfTasks(taskList));
    }

    /**
     * Formats and prints message for the today command.
     * 
     * @param taskList
     */
    public void todayMsg(List<Task> taskList) {
        Printer.printFormatReplyMsg("Here are your tasks for today~"
                + listOfTasks(taskList));
    }
    
    private String listOfTasks(List<Task> taskList) {
        StringBuilder msg = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            msg.append("\n");
            msg.append(i + 1);
            msg.append(". " + taskList.get(i));
        }

        return msg.toString();
    }

    /**
     * Formats and prints the message for storing of task.
     * 
     * @param task
     * @param size size of the tasklist
     */
    public void storeTaskMsg(Task task, int size) {
        StringBuilder msg = new StringBuilder("This task has been stored~\n")
                .append(task.toString())
                .append("\nYou currently have ")
                .append(size + " Tasks");
        Printer.printFormatReplyMsg(msg.toString());
    }

    /**
     * Formats and prints the message for deleting of task.
     * 
     * @param task
     */
    public void deleteMsg(Task task) {
        Printer.printFormatReplyMsg("This task has been deleted~\n"
        + task.toString());
    }

    /**
     * Formats and prints the message for marking of task.
     * 
     * @param task
     */
    public void markMsg(Task task) {
        Printer.printFormatReplyMsg("This task has been marked as completed~\n"
        + task.toString());
    }

    /**
     * Formats and prints the message for unmarking of task.
     * 
     * @param task
     */
    public void unmarkMsg(Task task) {
        Printer.printFormatReplyMsg("This task has been reverted to incomplete~\n"
        + task.toString());
    }
}
