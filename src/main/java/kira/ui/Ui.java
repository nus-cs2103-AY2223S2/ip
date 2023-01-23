package kira.ui;

import java.util.List;

import kira.task.Task;

public class Ui {

    public void start() {
        String startMsg = "Hi! I am KiraBot~\n"
                + "How may I help you today?";
        Printer.printFormatReplyMsg(startMsg);
    }

    public void end() {
        Printer.printFormatReplyMsg("Goodbye! Have a nice day~");
    }

    public void errMsg(String msg) {
        Printer.printFormatErrorMsg(msg);
    }

    public void listMsg(List<Task> taskList) {
        Printer.printFormatReplyMsg("Here are all the items stored~"
                + listOfTasks(taskList));
    }

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

    public void storeTaskMsg(Task task, int size) {
        StringBuilder msg = new StringBuilder("This task has been stored~\n")
                .append(task.toString())
                .append("\nYou currently have ")
                .append(size + " Tasks");
        Printer.printFormatReplyMsg(msg.toString());
    }

    public void deleteMsg(Task task) {
        Printer.printFormatReplyMsg("This task has been deleted~\n"
        + task.toString());
    }

    public void markMsg(Task task) {
        Printer.printFormatReplyMsg("This task has been marked as completed~\n"
        + task.toString());
    }

    public void unmarkMsg(Task task) {
        Printer.printFormatReplyMsg("This task has been reverted to incomplete~\n"
        + task.toString());
    }
}
